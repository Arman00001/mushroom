package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mushroom.enums.Destination
import com.example.mushroom.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomePageViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _popupHarvestState = MutableStateFlow<PopupHarvestState>(PopupHarvestState(false))
    val popupHarvestState = _popupHarvestState.asStateFlow()

    init {
        println(dataRepository.getData())
        _uiState.value = HomePageUiState.Success
    }

    fun onDestinationClicked(destination: Destination) {
        println("CLICKED " + destination.contentDescription)
    }

    fun onClickTest() {
        _popupHarvestState.update {
            it.copy(isOpen = true)
        }
    }

    fun dismissPopup() {
        _popupHarvestState.update {
            it.copy(isOpen = false)
        }
    }

}

sealed class HomePageUiState {
    data object Loading : HomePageUiState()
    data object Failed : HomePageUiState()
    data object Success : HomePageUiState()
}

data class PopupHarvestState(val isOpen: Boolean)