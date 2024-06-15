package com.olimhousestudio.buspedia.data.repository

import android.util.Log
import com.olimhousestudio.buspedia.data.source.remote.ApiInterface
import com.olimhousestudio.buspedia.data.source.remote.model.bus.BusModel
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody

class BusRepositoryImplementation(private var apiInterface: ApiInterface) : BusRepository {
    override fun createBus(createBusBody: RequestBody): Flow<ResponseState<Boolean>> = flow {
        emit(ResponseState.Loading)
        try {
            apiInterface.createBus(body = createBusBody)
            emit(ResponseState.Success(true))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override fun getAllBus(): Flow<ResponseState<BusModel>> = flow {
        emit(ResponseState.Loading)
        try {
            val result = apiInterface.getAllBus()
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            Log.d("TAG", "getAllBus: ${e.message}")
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override fun getBusById(id: String): Flow<ResponseState<BusModel>> = flow {
        emit(ResponseState.Loading)
        try {
            val result = apiInterface.getDetailBus(id)
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            Log.d("TAG", "getBusById: ${e.message}")
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override fun getBusByUuid(id: String): Flow<ResponseState<BusModel>> = flow {
        emit(ResponseState.Loading)
        try {
            val result = apiInterface.getAdminBus(id)
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            Log.d("TAG", "getBusById: ${e.message}")
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override fun updateBus(id: String, updateBusBody: RequestBody): Flow<ResponseState<Boolean>> =
        flow {
            emit(ResponseState.Loading)
            try {
                val result = apiInterface.updateBus(id = id, body = updateBusBody)
                emit(ResponseState.Success(true))
            } catch (e: Exception) {
                emit(ResponseState.Error(e.message.toString()))
            }
        }


    override fun deleteBus(id: String): Flow<ResponseState<Boolean>> = flow {
        emit(ResponseState.Loading)
        try {
            apiInterface.deleteBus(id)
            emit(ResponseState.Success(true))
        } catch (e: Exception) {
            Log.d("TAG", "deleteBus: ${e.message}")
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}