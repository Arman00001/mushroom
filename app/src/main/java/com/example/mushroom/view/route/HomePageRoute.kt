package com.example.mushroom.view.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mushroom.enums.ErrorType
import com.example.mushroom.ui.component.ErrorScreen
import com.example.mushroom.ui.component.LoadingScreen
import com.example.mushroom.view.screen.HomePageScreen
import com.example.mushroom.view.screen.data.ShelfData
import com.example.mushroom.view.screen.data.WarningData
import com.example.mushroom.viewmodel.HomePageUiState
import com.example.mushroom.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePageRoute(
    viewModel: HomePageViewModel = koinViewModel(),
    onShelfClick: (Int) -> Unit,
    onMonitoringClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        HomePageUiState.Loading -> LoadingScreen()
        HomePageUiState.Failed -> ErrorScreen { Unit }// viewModel.retry() }
        HomePageUiState.Success -> HomePageScreen(
            allShelves = addShelves(),
            onShelfClick = onShelfClick,
            onMonitoringClick = onMonitoringClick
        )
    }
}


fun addShelves(): List<ShelfData> {
    val warnings = listOf(
        WarningData("das", ErrorType.ERROR),
        WarningData("DSASD", ErrorType.WARNING),
        WarningData("asddsa", ErrorType.WARNING),
        WarningData("adsfgndjskfsijdnf", ErrorType.ERROR)
    )
    val allShelves = listOf(
        ShelfData(
            id = 1,
            linked = true,
            humidity = 45.0,
            temperature = 22.5,
            pressure = 1013.2,
            charge = 85,
//            warnings = warnings
        ),
        ShelfData(
            id = 2,
            linked = false,
            humidity = 50.3,
            temperature = 21.0,
            pressure = 1012.8,
            charge = 60,
//            warnings = warnings
        ),
        ShelfData(
            id = 3,
            linked = true,
            humidity = 38.7,
            temperature = 23.1,
            pressure = 1014.5,
            charge = 95,
//            warnings = warnings
        ),
        ShelfData(
            id = 4,
            linked = true,
            humidity = 64.2,
            temperature = 28.4,
            pressure = 1011.9,
            charge = 40
        ),
        ShelfData(
            id = 5,
            linked = false,
            humidity = 42.8,
            temperature = 33.3,
            pressure = 1013.7,
            charge = 70
        )
    )
    return allShelves
}