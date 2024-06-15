package com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olimhousestudio.buspedia.data.repository.AuthenticationRepository
import com.olimhousestudio.buspedia.data.source.remote.model.auth.Auth
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class SignUpViewModel(private val repository: AuthenticationRepository) : ViewModel() {
    private val _signUpState = MutableStateFlow<ResponseState<Auth>>(ResponseState.Idle)

    val signUpState = _signUpState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Idle
    )

    fun signUpUser(user: JsonObject) {
        viewModelScope.launch {
            _signUpState.emitAll(
                repository.signUp(
                    signUpBody = user
                        .toString()
                        .toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
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

    var password by mutableStateOf("")
        private set

    fun updatePassword(input: String) {
        password = input
    }

    var confirmPassword by mutableStateOf("")
        private set

    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }

    var isAdmin by mutableStateOf(false)
        private set

    fun updateIsAdmin(input: Boolean) {
        isAdmin = input
    }
}