package com.example.mushroom.view.screen.shelfdetails.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mushroom.R
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.icon.VariablesError

@Composable
fun ShelfIdScreen(
    manipulatorId: Int,
    linkedStatus: Boolean,
    coordinateX: Int,
    coordinateY: Int,
    coordinateZ: Int,
    downloadSpeed: Int,
    pressure: Int,
    batteryPercentage: Int
) {
    ShelfIdContent(
        manipulatorId = manipulatorId,
        linkedStatus = linkedStatus,
        coordinateX = coordinateX,
        coordinateY = coordinateY,
        coordinateZ = coordinateZ,
        downloadSpeed = downloadSpeed,
        pressure = pressure,
        batteryPercentage = batteryPercentage
    )
}

@Composable
fun ShelfIdContent(
    manipulatorId: Int,
    linkedStatus: Boolean,
    coordinateX: Int,
    coordinateY: Int,
    coordinateZ: Int,
    downloadSpeed: Int,
    pressure: Int,
    batteryPercentage: Int
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Manipulator ID: $manipulatorId",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Spacer(Modifier.weight(1f))

            IconButton(onClick = { println("click") }) {
                Icon(
                    tint = Color.Unspecified,
                    painter = painterResource(R.drawable.success),
                    contentDescription = "close"
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(44.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            RoundedIconButton(
                painter = painterResource(R.drawable.status_link),
                contentDescription = "link status",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.coordinates),
                contentDescription = "coordinates",
                text = "X$coordinateX Y$coordinateY Z$coordinateZ",
                textFontWeight = FontWeight.SemiBold,
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.infospeed),
                text = "$downloadSpeed MB/S",
                textFontWeight = FontWeight.SemiBold,
                contentDescription = "download speed",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.pressure),
                text = "$pressure%",
                textFontWeight = FontWeight.SemiBold,
                contentDescription = "pressure",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.battery),
                text = "$batteryPercentage%",
                textFontWeight = FontWeight.SemiBold,
                contentDescription = "battery",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
            )

        }
    }
}