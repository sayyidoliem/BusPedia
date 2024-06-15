package com.olimhousestudio.buspedia.data.repository

import android.util.Log
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.data.source.remote.ApiInterface
import com.olimhousestudio.buspedia.data.source.remote.model.auth.Auth
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody

class AuthRepositoryImplementation(private var apiInterface: ApiInterface) :
    AuthenticationRepository {
    override fun signUp(signUpBody: RequestBody): Flow<ResponseState<Auth>> = flow {
        emit(ResponseState.Loading)
        try {
            val signUpResult = apiInterface.userSignUp(signUpBody)
            emit(ResponseState.Success(signUpResult))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override fun userLogin(loginBody: RequestBody): Flow<ResponseState<Auth>> = flow {
        emit(ResponseState.Loading)
        try {
            val loginResult = apiInterface.userLogin(loginBody)
            val user = apiInterface.getAllUser(id = "eq.${loginResult.user.id}")
            Log.d("login id", loginResult.user.id)
            KotprefLocalStorage.apply {
                this.userUid = user.first().id//get first array
                this.accessToken = loginResult.accessToken
                this.isAdmin = user.first().isAdmin
                this.username = user.first().userName
                this.email = user.first().userEmail
                this.created = user.first().createdAt
            }
            emit(ResponseState.Success(loginResult))
        } catch (e: Exception) {
            Log.d("login id", e.toString())
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}