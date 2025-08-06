package com.example.mushroom.view.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.MonitoringScreen
import com.example.mushroom.viewmodel.MonitoringUiState
import com.example.mushroom.viewmodel.MonitoringViewModel

@Composable
fun MonitoringRoute(
    navController: NavHostController,
    viewModel: MonitoringViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    when(uiState){
        MonitoringUiState.Failed -> ErrorScreen {  }
        MonitoringUiState.Loading -> LoadingScreen()
        MonitoringUiState.Success -> MonitoringScreen(listOf(1,2,3,4,5),1)
    }
}