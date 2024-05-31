package com.olimhousestudio.buspedia.di

import com.olimhousestudio.buspedia.data.repository.BusRepository
import com.olimhousestudio.buspedia.data.repository.BusRepositoryImplementation
import com.olimhousestudio.buspedia.data.source.remote.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val getApi: ApiInterface
    val getRepository: BusRepository
}

class AppModuleImpl : AppModule {
    override val getApi: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://zqcfvxvyyqerifoleito.supabase.co/rest/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    override val getRepository: BusRepository by lazy {
        BusRepositoryImplementation(getApi)
    }

}