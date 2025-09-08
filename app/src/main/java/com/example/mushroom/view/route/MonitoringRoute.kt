package com.example.mushroom.view.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.MonitoringScreen
import com.example.mushroom.viewmodel.MonitoringUiState
import com.example.mushroom.viewmodel.MonitoringViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MonitoringRoute(
    shelfIds: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
    currentShelfId: Int,
    onBack: () -> Unit
) {
    val viewModel: MonitoringViewModel = koinViewModel(
        key = "ShelfDetailsViewModel_$currentShelfId",
        parameters = { parametersOf(currentShelfId, shelfIds) }
    )

    val uiState by viewModel.uiState.collectAsState()
    val sidebar by viewModel.monitoringShelvesUiState.collectAsState()


    when (uiState) {
        MonitoringUiState.Failed -> ErrorScreen { }
        MonitoringUiState.Loading -> LoadingScreen()
        MonitoringUiState.Success -> MonitoringScreen(
            shelfIDs = sidebar.shelfIds,
            currentShelfId = sidebar.selectedShelfId,
            onBack = onBack,
            selectShelf = viewModel::selectShelf
        )
    }
}