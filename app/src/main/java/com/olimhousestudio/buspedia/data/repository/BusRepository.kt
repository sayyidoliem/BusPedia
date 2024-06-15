package com.olimhousestudio.buspedia.data.repository

import com.olimhousestudio.buspedia.data.source.remote.model.bus.BusModel
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface BusRepository {

    fun createBus(createBusBody : RequestBody): Flow<ResponseState<Boolean>>

    fun getAllBus(): Flow<ResponseState<BusModel>>

    fun getBusById(id: String): Flow<ResponseState<BusModel>>

    fun getBusByUuid(id: String): Flow<ResponseState<BusModel>>

    fun updateBus(id: String, updateBusBody : RequestBody): Flow<ResponseState<Boolean>>

    fun deleteBus(id: String): Flow<ResponseState<Boolean>>

}