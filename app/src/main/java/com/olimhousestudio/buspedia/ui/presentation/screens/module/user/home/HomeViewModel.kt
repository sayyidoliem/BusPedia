package com.olimhousestudio.buspedia.ui.presentation.screens.module.user.home

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

class HomeViewModel(private val repository: BusRepository) : ViewModel() {

    private val _homeState = MutableStateFlow<ResponseState<BusModel>>(ResponseState.Idle)

    val homeState = _homeState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResponseState.Idle
    )

    fun getData() {
        viewModelScope.launch {
            _homeState.emitAll(repository.getAllBus())
        }
    }

    fun getDataDetail(id: Int) {
        viewModelScope.launch {
            _homeState.emitAll(repository.getBusById("eq.$id"))//use e.q for read filter supabase
        }
    }
}