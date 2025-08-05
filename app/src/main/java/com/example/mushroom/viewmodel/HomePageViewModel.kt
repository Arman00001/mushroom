package com.example.mushroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mushroom.enums.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomePageViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<HomePageUiState>(HomePageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = HomePageUiState.Success
    }

    fun onDestinationClicked(destination: Destination){
        println("CLICKED " + destination.contentDescription)
    }
}

sealed class HomePageUiState{
    data object Loading: HomePageUiState()
    data object Failed: HomePageUiState()
    data object Success: HomePageUiState()
}