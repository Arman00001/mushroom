package com.example.mushroom.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import com.example.mushroom.enums.Destination
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.ShelfIcon

@Composable
fun MonitoringScreen(
    shelfIDs: List<Int>,
    currentShelfId: Int
) {
    MonitoringScreenContent(shelfIDs, onDestinationClick = {}, currentShelfId)
}

@Composable
fun MonitoringScreenContent(
    shelfIDs: List<Int>,
    onDestinationClick: (Destination) -> Unit,
    currentShelfId: Int
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(1) }
    var currentStep by rememberSaveable { mutableIntStateOf(0) }
    val pageColor = COLORS.PageColor

    val bottomItems = Destination.entries.take(2)
    Scaffold(
        modifier = Modifier,
        containerColor = pageColor,

        ) { contentPadding ->
        Row {
            NavigationRail(
                modifier = Modifier.padding(contentPadding),
                containerColor = pageColor
            ) {
                shelfIDs.forEachIndexed { index, id ->
                    val ind = index + 1
                    ShelfIcon(
                        number = ind,
                        selected = ind == selectedDestination,
                        onIconClick = {
                            println("index: $ind\nId: $id")
                        }
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

            Column(
                modifier = Modifier
                    .weight(1f)                 // take the rest of the width
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = { println("go back") },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = "Monitoring",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Manipulator ID: $currentShelfId",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.weight(1f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(onClick = { println("notifs clicked") }) {
                            Icon(
                                tint = Color.Unspecified,
                                painter = painterResource(R.drawable.notifications),
                                contentDescription = "notifications"
                            )
                        }

                        IconButton(onClick = {
                            println("menu clicked")
                            currentStep++
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

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(fraction = 0.75f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StepProgressIndicator(
                        listOf(
                            painterResource(R.drawable.mini_test_hand),
                            painterResource(R.drawable.mini_test_camera),
                            painterResource(R.drawable.mini_test_sensor),
                            painterResource(R.drawable.pressure),
                            painterResource(R.drawable.battery)
                        ),
                        currentStep = currentStep
                    )


                }
                IconButton(
                    onClick = { currentStep++ }
                ) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.status_link),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
fun StepProgressIndicator(
    painters: List<Painter>,
    currentStep: Int,
    modifier: Modifier = Modifier,
    completedColor: Color = Color("#46995E".toColorInt()),
    activeColor: Color = Color("#3A6B50".toColorInt()),
    upcomingColor: Color = Color("#C7C5B4".toColorInt()),
    iconSize: Dp = 22.dp,
    lineHeight: Dp = 4.dp,
    circleSize: Dp = 40.dp
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        painters.forEachIndexed { index, painter ->
            val circleColor = when {
                index < currentStep -> completedColor
                index == currentStep -> activeColor
                else -> upcomingColor
            }
            val iconTint = if (index > currentStep) Color.Unspecified else Color.White

            Box(
                modifier = Modifier
                    .size(circleSize)
                    .background(circleColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    tint = iconTint,
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.size(iconSize)
                )
            }

            // 2) Connector bar (skip after the last icon)
            if (index < painters.lastIndex) {
                // Bar color: completed if fully behind a completed step
                val barColor = if (index < currentStep) completedColor else upcomingColor
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(lineHeight)
                        .background(barColor)
                )
            }
        }
    }
}

@Preview
@Composable
fun MonitoringScreenPreview() {
    MonitoringScreen(
        listOf(1, 2, 3, 4, 5), 1
    )
}