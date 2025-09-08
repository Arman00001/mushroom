package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MonitoringViewModel(
    initialId: Int,
    shelfIds: List<Int>
): ViewModel(){

    data class MonitoringUiStateSidebar(
        val shelfIds: List<Int>,
        val selectedShelfId: Int
    )

    private val _monitoringShelvesUiState = MutableStateFlow(
        MonitoringUiStateSidebar(
            shelfIds,
            initialId
        )
    )

    val monitoringShelvesUiState = _monitoringShelvesUiState.asStateFlow()


    private val _uiState = MutableStateFlow<MonitoringUiState>(MonitoringUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value=MonitoringUiState.Success
    }

    fun selectShelf(id: Int){
        _monitoringShelvesUiState.update { it.copy(selectedShelfId = id) }
    }
}

sealed class MonitoringUiState{
    data object Loading: MonitoringUiState()
    data object Failed: MonitoringUiState()
    data object Success: MonitoringUiState()
}