package com.olimhousestudio.buspedia.data.repository

import android.util.Log
import com.olimhousestudio.buspedia.data.source.remote.ApiInterface
import com.olimhousestudio.buspedia.data.source.remote.model.BusModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BusRepositoryImplementation(private var apiInterface: ApiInterface) : BusRepository {
    override fun getAllBus(): Flow<List<BusModel>> = flow {
        try {
            val result = apiInterface.getAllBus()
        } catch (e: Exception) {
            Log.d("TAG", "getAllBus: ${e.message}")
        }
    }
}