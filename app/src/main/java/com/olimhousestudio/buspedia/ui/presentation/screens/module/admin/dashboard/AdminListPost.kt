package com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.olimhousestudio.buspedia.R
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.utils.ViewModelRefresh
import com.olimhousestudio.buspedia.utils.viewModelFactory

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AdminListPost(
    navigateBack: () -> Unit,
    navigateToEdit: (index: Int) -> Unit,
    viewModel: AdminDashboardViewModel = viewModel(
        factory = viewModelFactory {
            AdminDashboardViewModel(BusPediaApplication.appModule.getBusRepository)
        }
    )
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getDataPostAdmin(KotprefLocalStorage.userUid)
    }

    val adminListPost = viewModel.adminState.collectAsStateWithLifecycle()

    var index by remember { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current

    val navigator = rememberListDetailPaneScaffoldNavigator()

    BackHandler {
        navigator.canNavigateBack()
    }

    val viewModelRefresh = viewModel<ViewModelRefresh>()

    val isLoading by viewModelRefresh.isLoading.collectAsState()

    val refreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    ListDetailPaneScaffold(
        modifier = Modifier.fillMaxSize(),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane(modifier = Modifier.fillMaxSize()) {
                adminListPost.value.DisplayResult(
                    onLoading = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    onSuccess = { busModel ->
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text(text = "BusPedia") },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = { navigateBack() }) {
                                            Icon(
                                                modifier = Modifier.size(24.dp),
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = ""
                                            )
                                        }
                                    },
                                )
                            }
                        ) { it ->
                            SwipeRefresh(
                                state = refreshState,
                                onRefresh = { viewModelRefresh.loadStuff() },
                                indicator = { state, refreshTrigger ->
                                    SwipeRefreshIndicator(
                                        state = state,
                                        refreshTriggerDistance = refreshTrigger,
                                    )
                                }
                            ){
                                LazyColumn(modifier = Modifier.padding(it)) {
                                    items(busModel) { busModelItem ->
                                        ListItem(
                                            modifier = Modifier.clickable {
                                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                                viewModel.getDataDetail(busModelItem.id)
                                            },
                                            headlineContent = { Text(text = busModelItem.bus_name!!) },
                                            leadingContent = {
                                                if (busModelItem.bus_image!!.isEmpty()) {
                                                    Image(
                                                        modifier = Modifier
                                                            .size(56.dp)
                                                            .fillMaxSize(),
                                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                                        contentDescription = ""
                                                    )
                                                } else {
                                                    AsyncImage(
                                                        modifier = Modifier
                                                            .size(56.dp)
                                                            .fillMaxSize(),
                                                        model = busModelItem.bus_image,
                                                        contentDescription = ""
                                                    )
                                                }

                                            },
                                            supportingContent = {
                                                Text(
                                                    text = busModelItem.bus_description!!,
                                                    maxLines = 2
                                                )
                                            },
                                            overlineContent = { Text(text = busModelItem.bus_company!!) },
                                            trailingContent = {
                                                IconButton(
                                                    onClick = {
                                                        viewModel.deleteBus(busModelItem.id)
                                                        Toast.makeText(
                                                            context,
                                                            "success",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = ""
                                                    )
                                                }
                                            },
                                        )
                                        HorizontalDivider(
                                            modifier = Modifier.padding(horizontal = 16.dp),
                                            thickness = 2.dp
                                        )
                                    }
                                }
                            }
                        }
                    },
                    onError = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = it)
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                adminListPost.value.DisplayResult(
                    onLoading = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    onSuccess = { busItem ->
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text(text = "BusPedia") },
                                    navigationIcon = {
                                        IconButton(
                                            onClick = { navigator.navigateBack() }) {
                                            Icon(
                                                modifier = Modifier.size(24.dp),
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = ""
                                            )
                                        }
                                    },
                                )
                            }
                        ) { paddingValues ->
                            busItem.forEach { busModelItem ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Card(
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .aspectRatio(1 / 1f)
                                                .padding(16.dp),
                                            elevation = CardDefaults.cardElevation(
                                                defaultElevation = 16.dp
                                            )
                                        ) {
                                            if (busModelItem.bus_image!!.isEmpty()){
                                                Image(
                                                    modifier = Modifier.fillMaxSize(),
                                                    painter = painterResource(id = R.drawable.ic_launcher_background),
                                                    contentDescription = "",
                                                    contentScale = ContentScale.Crop)
                                            } else {
                                                AsyncImage(
                                                    modifier = Modifier.fillMaxSize(),
                                                    model = busModelItem.bus_image,
                                                    contentDescription = "",
                                                    contentScale = ContentScale.Crop)
                                            }
                                        }
                                    }
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 8.dp
                                        ),
                                        text = busModelItem.bus_name!!,
                                        fontSize = 32.sp,
                                        lineHeight = 32.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(modifier = Modifier.padding(horizontal = 16.dp), text = busModelItem.bus_company!!)
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 8.dp
                                        ),
                                        text = busModelItem.bus_description!!
                                    )
                                    ElevatedButton(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .align(Alignment.CenterHorizontally),
                                        onClick = {
                                            navigateToEdit(busModelItem.id)
                                            viewModel.updateBusName(busModelItem.bus_name)
                                            viewModel.updateBusImage(busModelItem.bus_image!!)
                                            viewModel.updateBusCompany(busModelItem.bus_company)
                                            viewModel.updateBusDescription(busModelItem.bus_description)
                                        }
                                    ) {
                                        Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Text(text = "Edit")
                                    }
                                }
                            }
                        }
                    },
                    onError = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = it)
                        }
                    }
                )
            }
        }
    )
}