package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShelfDetailsViewModel(
    initialId: Int,
    shelfIds: List<Int>
) : ViewModel() {
    data class ShelfDetailsUiStateSidebar(
        val shelfIds: List<Int>,
        val selectedShelfId: Int
    )

    private val _shelvesUiState = MutableStateFlow(
        ShelfDetailsUiStateSidebar(
            shelfIds,
            initialId
        )
    )
    val shelvesUiState = _shelvesUiState.asStateFlow()

    private val _uiState = MutableStateFlow<ShelfDetailsUiState>(ShelfDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = ShelfDetailsUiState.Success
    }

    fun selectShelf(id: Int){
        _shelvesUiState.update { it.copy(selectedShelfId = id) }
    }


}

sealed interface ShelfDetailsUiState {
    data object Loading : ShelfDetailsUiState
    data object Failed : ShelfDetailsUiState
    data object Success : ShelfDetailsUiState
}

