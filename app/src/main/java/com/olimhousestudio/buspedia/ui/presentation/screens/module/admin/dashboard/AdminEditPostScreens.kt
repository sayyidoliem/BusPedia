package com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.olimhousestudio.buspedia.R
import com.olimhousestudio.buspedia.utils.DateUtils
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.utils.viewModelFactory
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditPostScreens(
    navigateBack: () -> Unit,
    navigateToList: () -> Unit,
    index: Int,
    viewModel: AdminDashboardViewModel = viewModel(
        factory = viewModelFactory {
            AdminDashboardViewModel(BusPediaApplication.appModule.getBusRepository)
        }
    )
) {

    val context = LocalContext.current

    val dateState = rememberDatePickerState()

    val millisToLocalDate = dateState.selectedDateMillis?.let {
        DateUtils().convertMillisToLocalDate(it)
    }

    val dateToString = millisToLocalDate?.let {
        DateUtils().dateWithoutDayToString(millisToLocalDate)
    } ?: "Choose Date"

    val adminState = viewModel.adminAddState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Edit post") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.showConfirmationDialog() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .aspectRatio(1 / 1f)
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Column(modifier = Modifier.align(Alignment.Center)) {
                    FloatingActionButton(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Upload,
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = "Upload your image")
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Bus Name")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = viewModel.busName,
                onValueChange = { busName -> viewModel.updateBusName(busName) },
                placeholder = { Text(text = viewModel.busName) },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Company")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = viewModel.busCompany,
                onValueChange = { busCompany -> viewModel.updateBusCompany(busCompany) },
                placeholder = { Text(text = "Company") },
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier.weight(0.5f), text = "Author")
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Text(modifier = Modifier.weight(0.5f), text = "Date")
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = viewModel.busImage,
                    onValueChange = { busImage -> viewModel.updateBusImage(busImage) },
                    label = { Text(text = "author") },
                    readOnly = true
                )
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = "",
                    onValueChange = { viewModel.showDateDialog() },
                    readOnly = true,
                    placeholder = { Text(text = dateToString) },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.showDateDialog() }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Description")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = viewModel.busDescription,
                onValueChange = { busDescription -> viewModel.updateBusDescription(busDescription) },
                placeholder = { Text(text = "Description") },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    viewModel.updateBus(
                        id = index,
                        bus = buildJsonObject {
                            put("author_id", KotprefLocalStorage.userUid)
                            put("bus_name", viewModel.busName)
                            put("bus_image", viewModel.busImage)
                            put("bus_description", viewModel.busDescription)
                            put("bus_company", viewModel.busCompany)
                        }
                    )
                }
            ) {
                Text(text = "Post")
            }
        }
    }
    adminState.value.DisplayResult(
        onLoading = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        },
        onSuccess = { navigateToList() },
        onError = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = it)
            }
        }
    )
    when {
        viewModel.confirmationDialog.value -> {
            AlertDialog(
                onDismissRequest = { viewModel.hideConfirmationDialog() },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.hideConfirmationDialog()
                            navigateBack()
                        }
                    ) {
                        Text(text = "Discard")
                    }
                },
                title = { Text(text = "Confirmation") },
                icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "") },
                text = { Text("Are you sure you want to discard this changes?") },
                dismissButton = {
                    TextButton(
                        onClick = {
                            viewModel.hideConfirmationDialog()
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}