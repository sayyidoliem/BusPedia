package com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.olimhousestudio.buspedia.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.utils.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreens(
    navigate: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToEdit: () -> Unit,
    viewModel: AdminDashboardViewModel = viewModel(
        factory = viewModelFactory {
            AdminDashboardViewModel(BusPediaApplication.appModule.getBusRepository)
        }
    )
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Dashboard") },
                actions = {
                    IconButton(onClick = { navigateToProfile() }) {
                        Icon(imageVector = Icons.Default.PersonPin, contentDescription = "")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = { navigateToProfile() }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "total post"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "0"
                            )
                        }
                        Column {
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "author"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = KotprefLocalStorage.username
                            )
                        }
                    }
                }
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .size(120.dp),
                    onClick = { navigate() }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "Create new post"
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "My Post")
//                    TextButton(onClick = { navigateToEdit() }) {
//                        Text(text = "see all")
//                        Icon(
//                            imageVector = Icons.Default.KeyboardArrowRight,
//                            contentDescription = ""
//                        )
//                    }
                }
            }
            item {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .size(120.dp),
                    onClick = { navigateToEdit() }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "See All"
                        )
                    }
                }
            }
            // TODO: use this card for last post admin
//            item {
//                ElevatedCard(
//                    modifier = Modifier.padding(16.dp),
//                    onClick = { /*TODO*/ }
//                ) {
//                    Column {
//                        Image(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(180.dp),
//                            painter = painterResource(id = R.drawable.ic_launcher_background),
//                            contentDescription = "",
//                            contentScale = ContentScale.Crop
//                        )
//                        ListItem(
//                            headlineContent = { Text(text = "title") },
//                            supportingContent = { Text(text = "content") },
//                            trailingContent = {
//                                Text(text = "timestamp")
//                            }
//                        )
//                    }
//                }
//            }
        }
    }
}