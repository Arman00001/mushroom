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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mushroom.R
import com.example.mushroom.enums.Destination
import com.example.mushroom.enums.ErrorType
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.icon.ShelfIcon
import com.example.mushroom.view.screen.data.WarningData
import com.example.mushroom.view.screen.shelfdetails.cards.ShelfIdScreen
import com.example.mushroom.view.screen.shelfdetails.cards.ShelfLastTaskCollectScreen
import com.example.mushroom.view.screen.shelfdetails.cards.ShelfLastTaskTestingScreen
import com.example.mushroom.view.screen.shelfdetails.cards.ShelfNumberScreen
import java.time.LocalDateTime


@Composable
fun ShelfDetailsScreen(
    shelfIDs: List<Int>,
    currentShelfId: Int,
    onBack: () -> Unit,
    onMonitoringClick: (Int) -> Unit
) {
    ShelfDetailsContent(
        shelfIDs = shelfIDs,
        currentShelfId = currentShelfId,
        onDestinationClick = {},
        onBack = onBack,
        onMonitoringClick = onMonitoringClick
    )
}

@Composable
fun ShelfDetailsContent(
    shelfIDs: List<Int>,
    currentShelfId: Int,
    onBack: () -> Unit,
    onMonitoringClick: (Int) -> Unit,
    onDestinationClick: (Destination) -> Unit,
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(currentShelfId) }
    val pageColor = COLORS.PageColor
    val listState = rememberLazyListState()


    val bottomItems = Destination.entries.take(2)
    Scaffold(
        modifier = Modifier,
        containerColor = pageColor,

        ) { contentPadding ->
        Row {
            NavigationRail(
                modifier = Modifier,
                containerColor = pageColor
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxHeight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(shelfIDs) { index, id ->
                        val ind = index + 1
                        ShelfIcon(
                            number = ind,
                            selected = ind == selectedDestination,
                            onIconClick = {
                                println("index: $ind\nId: $id")
                                selectedDestination = ind
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                bottomItems.forEachIndexed { index, destination ->
                    NavigationRailItem(
                        selected = false,
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
                    .fillMaxHeight(fraction = 0.95f)
                    .width(1.dp),
                color = Color.Gray
            )

            Column(
                modifier = Modifier
                    .weight(1f)                 // take the rest of the width
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.padding(contentPadding),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = "Details of Shelf Nº$currentShelfId",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { onMonitoringClick(currentShelfId) }) {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(R.drawable.safe),
                                contentDescription = "safe"
                            )
                        }

                        IconButton(onClick = { println("notifs clicked") }) {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(R.drawable.notifications),
                                contentDescription = "notifications"
                            )
                        }

                        IconButton(onClick = {
                            println("menu clicked")
                        }) {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(R.drawable.menu),
                                contentDescription = "menu"
                            )
                        }
                    }
                }

                Spacer(Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {


                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        contentPadding = PaddingValues(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalItemSpacing = 8.dp
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                shape = RoundedCornerShape(25.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box {
                                    ShelfNumberScreen(
                                        currentShelfId = currentShelfId,
                                        humidity = 60,
                                        temperature = 25
                                    )
                                }
                            }
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                shape = RoundedCornerShape(25.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box {
                                    ShelfLastTaskCollectScreen(
                                        mushroomHeightLowerRange = 2,
                                        mushroomHeightUpperRange = 4,
                                        mushroomCount = 156,
                                        collectSpeed = 5,
                                        timeCollected = LocalDateTime.now(),
                                        warnings = addWarnings()
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                shape = RoundedCornerShape(25.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box {
                                    ShelfIdScreen(
                                        manipulatorId = currentShelfId, //later change to manipulatorId
                                        linkedStatus = true,
                                        coordinateX = 12,
                                        coordinateY = 33,
                                        coordinateZ = 24,
                                        downloadSpeed = 250,
                                        pressure = 25,
                                        batteryPercentage = 60
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                shape = RoundedCornerShape(25.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box {
                                    ShelfLastTaskTestingScreen(
                                        timeTested = LocalDateTime.now(),
                                        warnings = addWarnings()
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(contentPadding),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            RoundedIconButton(
                                painter = painterResource(R.drawable.run_test),
                                contentDescription = "run test",
                                onClick = { println("testing start") },
                                containerColor = COLORS.PopUpButtonContainer,
                                contentColor = Color.Unspecified,
                                borderWidth = (-1).dp,
                                cornerSize = 18.dp,
                                iconSize = 70.dp,
                                top = 20.dp,
                                bottom = 20.dp
                            )

                            RoundedIconButton(
                                painter = painterResource(R.drawable.testing2),
                                contentDescription = "test",
                                onClick = { println("testing") },
                                containerColor = COLORS.TestingContainer,
                                contentColor = Color.Unspecified,
                                borderWidth = (-1).dp,
                                cornerSize = 18.dp,
                                iconSize = 70.dp,
                                top = 20.dp,
                                bottom = 20.dp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(device = Devices.TABLET, showSystemUi = true)
@Composable
fun ShelfDetailsPreview() {
    ShelfDetailsScreen(
        shelfIDs = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
        currentShelfId = 2,
        onBack = { println("back") },
        onMonitoringClick = {}
    )
}


fun addWarnings():List<WarningData>{
    val warnings = listOf(
        WarningData("camera 2", ErrorType.ERROR),
        WarningData("warning", ErrorType.WARNING),
        WarningData("asddsa", ErrorType.WARNING),
        WarningData("adsfgndjskfsijdnf", ErrorType.ERROR),
        WarningData("adsfgndjskfsijdnf", ErrorType.ERROR),
        WarningData("adsfgndjskfsijdnf", ErrorType.ERROR))
    return warnings
}