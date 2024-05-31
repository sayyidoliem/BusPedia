package com.olimhousestudio.buspedia.data.repository

import com.olimhousestudio.buspedia.data.source.remote.model.BusModel
import kotlinx.coroutines.flow.Flow

interface BusRepository {
    fun getAllBus(): Flow<List<BusModel>>
}