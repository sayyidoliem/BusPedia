package com.olimhousestudio.buspedia.data.repository

import com.olimhousestudio.buspedia.data.source.remote.model.auth.Auth
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface AuthenticationRepository {

    fun signUp(signUpBody : RequestBody): Flow<ResponseState<Auth>>

    fun userLogin(loginBody: RequestBody): Flow<ResponseState<Auth>>

}