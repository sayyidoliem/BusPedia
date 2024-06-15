package com.olimhousestudio.buspedia.data.source.remote

import com.olimhousestudio.buspedia.data.source.remote.model.auth.Auth
import com.olimhousestudio.buspedia.data.source.remote.model.bus.BusModel
import com.olimhousestudio.buspedia.data.source.remote.model.bus.BusModelItem
import com.olimhousestudio.buspedia.data.source.remote.model.user.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("rest/v1/bis")
    suspend fun createBus(@Body body: RequestBody)

    @GET("rest/v1/bis")
    suspend fun getAllBus(@Query(value = "select") select: String = "*"): BusModel

    @GET("rest/v1/bis")
    suspend fun getDetailBus(
        @Query(value = "id") id: String = "",
        @Query(value = "select") select: String = "*"
    ): BusModel

    @GET("rest/v1/bis")
    suspend fun getAdminBus(
        @Query(value = "author_id") id: String = "",
        @Query(value = "select") select: String = "*"
    ): BusModel

    @PATCH("rest/v1/bis")
    suspend fun updateBus(
        @Query(value = "id") id: String,
        @Body body: RequestBody
    )

    @DELETE("rest/v1/bis")
    suspend fun deleteBus(@Query(value = "id") id: String)

    @POST("auth/v1/signup")
    suspend fun userSignUp(
        @Body body: RequestBody,
    ): Auth

    @POST("auth/v1/token?grant_type=password")
    suspend fun userLogin(
        @Body body: RequestBody
    ): Auth

    @GET("rest/v1/users")
    suspend fun getAllUser(
        @Query(value = "id") id: String,
        @Query(value = "select") select: String = "*"
    ): User

}