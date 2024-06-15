package com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.olimhousestudio.buspedia.R
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.utils.viewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProfileScreens(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: AdminProfileViewModel = viewModel(
        factory = viewModelFactory {
            AdminProfileViewModel(BusPediaApplication.appModule.getAuthRepository)
        }
    )
) {

//    var checked by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(modifier = Modifier.padding(start = 16.dp, top = 16.dp), text = "Account")
                Spacer(modifier = Modifier.padding(8.dp))
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    overlineContent = { Text(text = "Name") },
                    headlineContent = { Text(text = KotprefLocalStorage.username) }
                )
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    overlineContent = { Text(text = "E-Mail") },
                    headlineContent = { Text(text = KotprefLocalStorage.email) }
                )
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    overlineContent = { Text(text = "Type") },
                    headlineContent = {
                        Text(text = "Admin"
                        )
                    }
                )
//                ElevatedButton(
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(16.dp),
//                    onClick = { viewModel.showEditDialog() }) {
//                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
//                    Spacer(modifier = Modifier.padding(4.dp))
//                    Text(text = "Edit")
//                }
            }
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//            ) {
//                Text(modifier = Modifier.padding(start = 16.dp, top = 16.dp), text = "Settings")
//                Spacer(modifier = Modifier.padding(8.dp))
//                ListItem(
//                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                    overlineContent = { Text(text = "Appearance") },
//                    headlineContent = { Text(text = "Switch theme") },
//                    trailingContent = {
//                        Switch(checked = checked, onCheckedChange = { checked = it })
//                    }
//                )
//                ListItem(
//                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                    overlineContent = { Text(text = "Type account") },
//                    headlineContent = { Text(text = "Change your type account") },
//                    trailingContent = {
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Icon(imageVector = Icons.Default.OpenInNew, contentDescription = "")
//                        }
//                    }
//                )
//            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                onClick = { viewModel.showConfirmationDialog() }
            ) {
                Text(text = "Sign Out")
            }
        }
    }
    when {
        viewModel.confirmationDialog.value -> {
            AlertDialog(
                onDismissRequest = { viewModel.hideConfirmationDialog() },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        onClick = {
                            viewModel.hideConfirmationDialog()
                            KotprefLocalStorage.clear()
                            navigateToLogin()
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                },
                title = { Text(text = "Confirmation") },
                icon = { Icon(imageVector = Icons.Default.Warning, contentDescription = "") },
                text = { Text("Are you sure you want to log out from your account?") },
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

        viewModel.editDialog.value -> {
            Dialog(onDismissRequest = { viewModel.hideEditDialog() }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "sdfsdufds")
                        Button(
                            onClick = {
                                KotprefLocalStorage.apply {
//                    this.isAdmin = user.first().isAdmin
                                    this.username = viewModel.userName
//                    this.email = user.first().userEmail
                                    viewModel.hideEditDialog()
                                }
                            }
                        ) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }
    }
}