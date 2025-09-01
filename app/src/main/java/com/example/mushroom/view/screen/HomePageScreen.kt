package com.example.mushroom.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mushroom.R
import com.example.mushroom.enums.Destination
import com.example.mushroom.ui.component.PopupErrorBox
import com.example.mushroom.ui.component.PopupTestingBox
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.route.addShelves
import com.example.mushroom.view.screen.data.ShelfData
import com.example.mushroom.viewmodel.HomePageViewModel
import com.example.mushroom.viewmodel.ShelfViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
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
        onMonitoringClick = onMonitoringClick,
        onRunHarvestClick = viewModel::onClickTest
    )

    val popup by shelfViewModel.popupState.collectAsStateWithLifecycle()

    PopupErrorBox(
        popup.id, popup.isOpen, onDismiss = shelfViewModel::dismissPopup
    )

    val harvestPopup by viewModel.popupHarvestState.collectAsStateWithLifecycle()

    PopupTestingBox(
        open = harvestPopup.isOpen,
        onDismiss = viewModel::dismissPopup,
        shelfIds = allShelves.map { shelf ->
            shelf.id
        }
    )

}

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    allShelves: List<ShelfData>,
    onDestinationClick: (Destination) -> Unit,
    onShelfClick: (Int) -> Unit,
    onMonitoringClick: (Int) -> Unit,
    onRunHarvestClick: () -> Unit
) {
    val selectedDestination by rememberSaveable { mutableIntStateOf(-1) }
    val destinations = Destination.entries

    val topItems = destinations.take(2)

    val pageColor = COLORS.PageColor

    Scaffold(
        modifier = modifier,
        containerColor = pageColor
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

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (allShelves.any { !it.warnings.isNullOrEmpty() }) {
                        RoundedIconButton(
                            painter = painterResource(R.drawable.run_test),
                            contentColor = Color("#E7AE8B".toColorInt()),
                            containerColor = Color("#F1D0BC".toColorInt()),
                            contentDescription = "Run test disabled",
                            borderWidth = (-1).dp,
                            cornerSize = 18.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 12.dp,
                            end = 12.dp,
                            onClick = { println("run test disabled") }
                        )
                    } else {
                        RoundedIconButton(
                            painter = painterResource(R.drawable.run_test),
                            contentColor = Color.White,
                            containerColor = Color("#CC5308".toColorInt()),
                            contentDescription = "Run test",
                            borderWidth = (-1).dp,
                            cornerSize = 18.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 12.dp,
                            end = 12.dp,
                            onClick = onRunHarvestClick
                        )
                    }

                    RoundedIconButton(
                        painter = painterResource(R.drawable.testing2),
                        contentDescription = "test",
                        onClick = { println("testing") },
                        containerColor = COLORS.TestingContainer,
                        contentColor = Color.Unspecified,
                        borderWidth = (-1).dp,
                        cornerSize = 18.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                }
            }

            VerticalDivider(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .fillMaxHeight(fraction = 0.94f)
                    .width(1.dp),
                color = Color("#C7C5B4".toColorInt())
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentPadding = PaddingValues(15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(allShelves) { shelf ->
                    val shape = RoundedCornerShape(25.dp)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()           // each card fills its column
                            .padding(8.dp),
                        shape = shape,             // rounded corners
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White              // pure white background
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),  // subtle drop shadow
                        onClick = { onShelfClick(shelf.id) }
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
        onMonitoringClick = { println("monitoring") },
    )
}