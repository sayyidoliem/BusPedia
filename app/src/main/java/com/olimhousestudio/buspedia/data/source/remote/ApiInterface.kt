package com.olimhousestudio.buspedia.data.source.remote

import com.olimhousestudio.buspedia.data.source.remote.model.BusModel
import retrofit2.http.GET

interface ApiInterface {

    @GET("bis?select=*")
    suspend fun getAllBus() : BusModel

}