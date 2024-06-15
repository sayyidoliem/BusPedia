package com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.buspedia.BusPediaApplication
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.utils.viewModelFactory
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Composable
fun SignUpScreens(
    navigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = viewModel(
        factory = viewModelFactory {
            SignUpViewModel(BusPediaApplication.appModule.getAuthRepository)
        }
    )
) {

    val context = LocalContext.current

    val signUpState = viewModel.signUpState.collectAsStateWithLifecycle()

    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Sign Up",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Register your Account first")
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Username")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.userName,
            onValueChange = { username -> viewModel.updateUserName(username) },
            placeholder = { Text(text = "username") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Name")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.name,
            onValueChange = { username -> viewModel.updateName(username) },
            placeholder = { Text(text = "name") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "E-Mail")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.email,
            onValueChange = { email -> viewModel.updateEmail(email) },
            placeholder = { Text(text = "email") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Password")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.password,
            onValueChange = { password -> viewModel.updatePassword(password) },
            placeholder = { Text(text = "password") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Confirm Password")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.confirmPassword,
            onValueChange = { confirmPassword -> viewModel.updateConfirmPassword(confirmPassword) },
            label = { Text(text = "confirm password") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "I'm an Author")
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                thumbContent = if (checked) {
                    {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "")
                    }
                } else {
                    null
                }
            )
        }
        Spacer(modifier = Modifier.padding(24.dp))
        var isAdmin = if (checked) true else false
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = viewModel.password == viewModel.confirmPassword,
            onClick = {
                viewModel.signUpUser(
                    buildJsonObject {
                        put("email", viewModel.email)
                        put("password", viewModel.password)
                        put("data", buildJsonObject {
                            put("username", viewModel.userName)
                            put("name", viewModel.name)
                        }
                        )
                    },
                )
            }
        ) {
            Text(text = "Register")
        }
        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            text = buildAnnotatedString {
                append("Already have an account? ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Login")
                }
            },
            onClick = {
                navigateToLogin()
            }
        )
    }
    signUpState.value.DisplayResult(
        onLoading = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        },
        onSuccess = {
            navigateToLogin()
        },
        onError = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = it)
            }
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    )
}