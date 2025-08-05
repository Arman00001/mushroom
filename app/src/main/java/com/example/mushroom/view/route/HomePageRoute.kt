package com.example.mushroom.view.route

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.HomePageScreen
import com.example.mushroom.viewmodel.HomePageUiState
import com.example.mushroom.viewmodel.HomePageViewModel

@Composable
fun HomePageRoute(
    navController: NavHostController,
    viewModel: HomePageViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        HomePageUiState.Loading -> LoadingScreen()
        HomePageUiState.Failed -> ErrorScreen { Unit }// viewModel.retry() }
        HomePageUiState.Success -> HomePageScreen(Modifier.padding(8.dp), navController)
    }
}