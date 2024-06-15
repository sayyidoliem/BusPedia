package com.olimhousestudio.buspedia.ui.presentation.screens.module.user.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.olimhousestudio.buspedia.data.repository.AuthenticationRepository

class ProfileViewModel(private val repository: AuthenticationRepository) : ViewModel() {

    var confirmationDialog = mutableStateOf(false)

    fun showConfirmationDialog() {
        confirmationDialog.value = true
    }

    fun hideConfirmationDialog() {
        confirmationDialog.value = false
    }

    var editDialog = mutableStateOf(false)

    fun showEditDialog() {
        editDialog.value = true
    }

    fun hideEditDialog() {
        editDialog.value = false
    }

    var userName by mutableStateOf("")
        private set

    fun updateUserName(input: String) {
        userName = input
    }

    var name by mutableStateOf("")
        private set

    fun updateName(input: String) {
        name = input
    }

    var email by mutableStateOf("")
        private set

    fun updateEmail(input: String) {
        email = input
    }
}