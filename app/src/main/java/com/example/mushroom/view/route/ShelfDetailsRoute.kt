package com.example.mushroom.view.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.ShelfDetailsScreen
import com.example.mushroom.viewmodel.ShelfDetailsUiState
import com.example.mushroom.viewmodel.ShelfDetailsViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ShelfDetailsRoute(
    shelfIds: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10),
    id: Int,
    onBack: () -> Unit,
    onMonitoringClick: (Int) -> Unit
) {
    val viewModel: ShelfDetailsViewModel = org.koin.androidx.compose.koinViewModel(
        key = "ShelfDetailsViewModel_$id",
        parameters = { parametersOf(id, shelfIds) }
    )
    val uiState by viewModel.uiState.collectAsState()
    val sidebar by viewModel.shelvesUiState.collectAsState()


    when (uiState) {
        ShelfDetailsUiState.Failed -> ErrorScreen { }
        ShelfDetailsUiState.Loading -> LoadingScreen()
        ShelfDetailsUiState.Success -> ShelfDetailsScreen(
            currentShelfId = sidebar.selectedShelfId,
            onBack = onBack,
            onMonitoringClick = { onMonitoringClick(sidebar.selectedShelfId) },
            shelfIDs = sidebar.shelfIds,
            selectShelf = viewModel::selectShelf
        )
    }
}