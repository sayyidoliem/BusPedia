package com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplashScreens(navigateToLogin: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Welcome to BusPedia")
        Text(text = "A bus encyclopedia app")
        Button(onClick = { navigateToLogin() }) {
            Text(text = "Next")
        }
    }
}