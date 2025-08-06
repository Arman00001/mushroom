package com.example.mushroom.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mushroom.enums.Destination
import com.example.mushroom.enums.ErrorType
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.screen.data.ShelfData
import com.example.mushroom.view.screen.data.WarningData
import com.example.mushroom.viewmodel.HomePageViewModel

@Composable
fun HomePageScreen(
    modifier: Modifier,
    navController: NavHostController,
//    allShelves: List<ShelfData>,
    viewModel: HomePageViewModel = viewModel()
) {
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
            warnings = warnings
        ),
        ShelfData(
            id = 2,
            linked = false,
            humidity = 50.3,
            temperature = 21.0,
            pressure = 1012.8,
            charge = 60,
            warnings = warnings
        ),
        ShelfData(
            id = 3,
            linked = true,
            humidity = 38.7,
            temperature = 23.1,
            pressure = 1014.5,
            charge = 95,
            warnings = warnings
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

    HomeScreenContent(
        modifier,
        navController,
        allShelves = allShelves,
        onDestinationClick = viewModel::onDestinationClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier,
    navController: NavHostController,
    allShelves: List<ShelfData>,
    onDestinationClick: (Destination) -> Unit
) {
    val selectedDestination by rememberSaveable { mutableIntStateOf(-1) }
    val destinations = Destination.entries

    // Split into top 2 and bottom 2
    val topItems = destinations.take(2)
    val bottomItems = destinations.drop(2)

    val pageColor = COLORS.PageColor

    Scaffold(
        modifier = modifier,
        containerColor = pageColor,
        topBar = {
            TopAppBar(
                title = { Text("Mushroom App") },
                colors = topAppBarColors(
                    containerColor = pageColor,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { contentPadding ->
        Row {
            NavigationRail(
                modifier = Modifier.padding(contentPadding),
                containerColor = pageColor
            ) {
                topItems.forEachIndexed { index, destination ->
                    NavigationRailItem(
                        selected = selectedDestination == index,
                        onClick = { onDestinationClick(destination) },
                        icon = {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(destination.icon),
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { destination.label }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                bottomItems.forEachIndexed { index, destination ->
                    NavigationRailItem(
                        selected = selectedDestination == index,
                        onClick = { onDestinationClick(destination) },
                        icon = {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(destination.icon),
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { destination.label }
                    )
                }


            }
            VerticalDivider(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .fillMaxHeight(fraction = 0.94f)
                    .width(1.dp),
                color = Color.Gray
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(allShelves) { shelf ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),           // each card fills its column
                        shape = RoundedCornerShape(8.dp),             // rounded corners
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White              // pure white background
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)  // subtle drop shadow
                    ) {
                        // optional inner padding
                        Box(Modifier.padding(12.dp)) {
                            ShelfScreen(shelf)
                        }
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenContentPreview() {
    HomePageScreen(
        modifier = Modifier.padding(8.dp),
        navController = rememberNavController(),
    )
}