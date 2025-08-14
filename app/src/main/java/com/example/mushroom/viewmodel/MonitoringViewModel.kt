package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mushroom.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MonitoringViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<MonitoringUiState>(MonitoringUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value=MonitoringUiState.Success
    }
}

sealed class MonitoringUiState{
    data object Loading: MonitoringUiState()
    data object Failed: MonitoringUiState()
    data object Success: MonitoringUiState()
}