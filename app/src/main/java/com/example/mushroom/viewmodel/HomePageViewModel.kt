package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mushroom.enums.Destination
import com.example.mushroom.repository.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomePageViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        println(dataRepository.getData())
        _uiState.value = HomePageUiState.Success
    }

    fun onDestinationClicked(destination: Destination) {
        println("CLICKED " + destination.contentDescription)
    }
}

sealed class HomePageUiState {
    data object Loading : HomePageUiState()
    data object Failed : HomePageUiState()
    data object Success : HomePageUiState()
}