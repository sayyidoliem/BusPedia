package com.olimhousestudio.buspedia.di

import com.olimhousestudio.buspedia.data.repository.AuthRepositoryImplementation
import com.olimhousestudio.buspedia.data.repository.AuthenticationRepository
import com.olimhousestudio.buspedia.data.repository.BusRepository
import com.olimhousestudio.buspedia.data.repository.BusRepositoryImplementation
import com.olimhousestudio.buspedia.data.source.remote.ApiInterface
import com.olimhousestudio.buspedia.utils.OkHttpInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val getApi: ApiInterface
    val getBusRepository: BusRepository
    val getAuthRepository: AuthenticationRepository
}

class AppModuleImpl : AppModule {
    override val getApi: ApiInterface by lazy {

        val client = OkHttpClient.Builder()
            .addInterceptor(OkHttpInterceptor())//get okHttp interceptor at utils

        Retrofit.Builder()
            .baseUrl("https://zqcfvxvyyqerifoleito.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ApiInterface::class.java)
    }
    override val getBusRepository: BusRepository by lazy {
        BusRepositoryImplementation(getApi)
    }
    override val getAuthRepository: AuthenticationRepository by lazy {
        AuthRepositoryImplementation(getApi)
    }
}

