package com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.login

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

class LoginViewModel(private val repository: AuthenticationRepository):ViewModel() {
    private val _loginState = MutableStateFlow<ResponseState<Auth>>(ResponseState.Idle)

    val loginState = _loginState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Idle
    )

    fun loginUser(user: JsonObject) {
        viewModelScope.launch {
            _loginState.emitAll(
                repository.userLogin(
                    loginBody = user.toString()
                        .toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }
}