package com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun LoginScreens(
    navigateToHome: () -> Unit,
    navigateToAdminDashboard: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = viewModel(
        factory = viewModelFactory {
            LoginViewModel(BusPediaApplication.appModule.getAuthRepository)
        }
    )
) {

    val context = LocalContext.current

    var username by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val loginState = viewModel.loginState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Login",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Please login your Account")
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "E-Mail")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "example@mail") },
//            suffix = { Text(text = ".com") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Password")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), onClick = {
                viewModel.loginUser(
                    buildJsonObject {
                        put("email", username)
                        put("password", password)
                    }
                )
            }
        ) {
            Text(text = "Login")
        }
        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            text = buildAnnotatedString {
                append("Don't have an account? ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Sign Up")
                }
            },
            onClick = {
                navigateToSignUp()
            }
        )
    }
    loginState.value.DisplayResult(
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
            if (KotprefLocalStorage.isAdmin == true) {
                navigateToAdminDashboard()
            } else {
                navigateToHome()
            }
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