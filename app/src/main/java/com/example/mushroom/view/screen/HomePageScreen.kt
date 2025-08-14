package com.example.mushroom.view.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mushroom.enums.Destination
import com.example.mushroom.ui.component.PopupBox
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.route.addShelves
import com.example.mushroom.view.screen.data.ShelfData
import com.example.mushroom.viewmodel.HomePageViewModel
import com.example.mushroom.viewmodel.ShelfViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePageScreen(
    modifier: Modifier,
    allShelves: List<ShelfData>,
    onShelfClick: (Int) -> Unit,
    onMonitoringClick: (Int) -> Unit,
    viewModel: HomePageViewModel = koinViewModel(),
    shelfViewModel: ShelfViewModel = koinViewModel()
) {
    HomeScreenContent(
        modifier,
        allShelves = allShelves,
        onDestinationClick = viewModel::onDestinationClicked,
        onShelfClick = onShelfClick,
        onMonitoringClick
    )

    val popup by shelfViewModel.popupState.collectAsStateWithLifecycle()

    PopupBox(
        popup.id, popup.isOpen, onDismiss = shelfViewModel::dismissPopup
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier,
    allShelves: List<ShelfData>,
    onDestinationClick: (Destination) -> Unit,
    onShelfClick: (Int) -> Unit,
    onMonitoringClick: (Int) -> Unit
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
                        modifier = Modifier
                            .fillMaxWidth()           // each card fills its column
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(onClick = {onShelfClick(shelf.id)} ),
                        shape = RoundedCornerShape(8.dp),             // rounded corners
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White              // pure white background
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)  // subtle drop shadow
                    ) {
                        // optional inner padding
                        Box(Modifier.padding(12.dp)) {
                            ShelfScreen(
                                shelfData = shelf,
                                onMonitoringClick = onMonitoringClick,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(name = "Tablet", device = TABLET, showSystemUi = true)
@Composable
fun HomeScreenContentPreview() {
    HomePageScreen(
        modifier = Modifier.padding(8.dp),
        addShelves(),
        onShelfClick = { println("shelf") },
        onMonitoringClick = { println("monitoring")},
    )
}