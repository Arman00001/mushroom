package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShelfDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ShelfDetailsUiState>(ShelfDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = ShelfDetailsUiState.Success
    }

}

sealed interface ShelfDetailsUiState {
    data object Loading : ShelfDetailsUiState
    data object Failed : ShelfDetailsUiState
    data object Success : ShelfDetailsUiState
}

