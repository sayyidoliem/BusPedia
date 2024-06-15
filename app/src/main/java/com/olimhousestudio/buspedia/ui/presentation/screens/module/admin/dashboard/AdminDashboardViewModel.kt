package com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olimhousestudio.buspedia.data.repository.BusRepository
import com.olimhousestudio.buspedia.data.source.remote.model.bus.BusModel
import com.rmaprojects.apirequeststate.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class AdminDashboardViewModel(private val repository: BusRepository) : ViewModel() {

    private val _adminAddState = MutableStateFlow<ResponseState<Boolean>>(ResponseState.Idle)

    val adminAddState = _adminAddState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Idle
    )

    fun createBus(bus: JsonObject) {
        viewModelScope.launch {
            _adminAddState.emitAll(
                repository.createBus(
                    createBusBody = bus.toString()
                        .toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    private val _adminState = MutableStateFlow<ResponseState<BusModel>>(ResponseState.Idle)

    val adminState = _adminState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Idle
    )

    fun getDataPostAdmin(id: String) {
        viewModelScope.launch {
            _adminState.emitAll(repository.getBusByUuid("eq.$id"))//use e.q for read filter supabase
        }
    }

    fun getDataDetail(id: Int) {
        viewModelScope.launch {
            _adminState.emitAll(repository.getBusById("eq.$id"))//use e.q for read filter supabase
        }
    }

    fun updateBus(id: Int, bus: JsonObject) {
        viewModelScope.launch {
            _adminAddState.emitAll(
                repository.updateBus(
                    id = "eq.$id",
                    updateBusBody = bus.toString()
                        .toRequestBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    fun deleteBus(id: Int) {
        viewModelScope.launch {
            _adminAddState.emitAll(repository.deleteBus("eq.$id"))
        }
    }

    var confirmationDialog = mutableStateOf(false)

    fun showConfirmationDialog() {
        confirmationDialog.value = true
    }

    fun hideConfirmationDialog() {
        confirmationDialog.value = false
    }

    var dateDialog = mutableStateOf(false)

    fun showDateDialog() {
        dateDialog.value = true
    }

    fun hideDateDialog() {
        dateDialog.value = false
    }

    var imageDialog = mutableStateOf(false)

    fun showImageDialog() {
        imageDialog.value = true
    }

    fun hideImageDialog() {
        imageDialog.value = false
    }

    var busName by mutableStateOf("")
        private set

    fun updateBusName(input: String) {
        busName = input
    }

    var busImage by mutableStateOf("")
        private set

    fun updateBusImage(input: String) {
        busImage = input
    }

    var busDescription by mutableStateOf("")
        private set

    fun updateBusDescription(input: String) {
        busDescription = input
    }

    var busCompany by mutableStateOf("")
        private set

    fun updateBusCompany(input: String) {
        busCompany = input
    }
}