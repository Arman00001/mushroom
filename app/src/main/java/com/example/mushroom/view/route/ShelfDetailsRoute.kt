package com.example.mushroom.view.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.ShelfDetailsScreen
import com.example.mushroom.viewmodel.ShelfDetailsUiState
import com.example.mushroom.viewmodel.ShelfDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ShelfDetailsRoute(
    viewModel: ShelfDetailsViewModel = koinViewModel(),
    id: Int,
    onBack: () -> Unit,
    onMonitoringClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        ShelfDetailsUiState.Failed -> ErrorScreen { }
        ShelfDetailsUiState.Loading -> LoadingScreen()
        ShelfDetailsUiState.Success -> ShelfDetailsScreen(
            currentShelfId = id,
            onBack = onBack,
            onMonitoringClick = onMonitoringClick,
            shelfIDs = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        )
    }
}