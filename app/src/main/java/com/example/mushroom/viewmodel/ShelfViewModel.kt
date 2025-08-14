package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mushroom.view.screen.data.WarningData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShelfViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ShelfUiState>(ShelfUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _popupState = MutableStateFlow<PopupState>(PopupState(false, 0))
    val popupState = _popupState.asStateFlow()

    init {
        _uiState.value = ShelfUiState.Success
    }

    fun onClickFailedTest(id: Int) {
        _popupState.update {
            it.copy(isOpen = true, id = id)
        }
    }

    fun dismissPopup() {
        _popupState.update {
            it.copy(isOpen = false, id = 0)
        }
    }

    fun clickSuccessTest(id: Int) {
        println("Success $id")
    }

    fun getShelfOpenCloseClickMethod(warnings: List<WarningData>?): (Int) -> Unit {
        if (warnings == null) return (this::clickSuccessTest)
        return this::onClickFailedTest
    }

}

sealed interface ShelfUiState {
    data object Loading : ShelfUiState
    data object Failed : ShelfUiState
    data object Success : ShelfUiState
}

data class PopupState(val isOpen: Boolean, val id: Int)