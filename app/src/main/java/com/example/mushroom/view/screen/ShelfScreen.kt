package com.example.mushroom.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mushroom.R
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.icon.VariablesError
import com.example.mushroom.view.screen.data.ShelfData

@Composable
fun ShelfScreen(shelfData: ShelfData) {
    ShelfContent(shelfData) { }
}

@Composable
fun ShelfContent(
    shelfData: ShelfData,
    onShelfClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Shelf N˚" + shelfData.id,
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = { println("DSA") }) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.safe),
                        contentDescription = "safe"
                    )
                }
                IconButton(onClick = { println("DSA")}) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.close),
                        contentDescription = "close"
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.height(44.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (shelfData.linked) {
                RoundedIconButton(
                    painter = painterResource(R.drawable.status_link),
                    contentDescription = "link status",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            } else {
                RoundedIconButton(
                    painter = painterResource(R.drawable.status_link_error),
                    contentDescription = "link status",
                    contentColor = Color.Red,
                    containerColor = VariablesError.statusesVErrorContainer,
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            }
            if (shelfData.humidity < 60) {
                RoundedIconButton(
                    painter = painterResource(R.drawable.humidity),
                    text = shelfData.humidity.toInt().toString() + " %",
                    contentDescription = "humidity",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            } else {
                RoundedIconButton(
                    painter = painterResource(R.drawable.humidity),
                    text = shelfData.humidity.toInt().toString() + " %",
                    contentDescription = "humidity",
                    contentColor = Color.Red,
                    containerColor = VariablesError.statusesVErrorContainer,
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            }
            shelfData.temperature?.let {
                if (it < 25) {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.celsius),
                        text = " " + it.toInt().toString(),
                        contentDescription = "temperature",
                        borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                        onClick = {}
                    )
                } else {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.celsius),
                        text = " " + it.toInt().toString(),
                        contentDescription = "temperature",
                        contentColor = Color.Red,
                        containerColor = VariablesError.statusesVErrorContainer,
                        borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                        onClick = {}
                    )
                }
            }
            shelfData.taskPercent?.let {
                RoundedIconButton(
                    painter = painterResource(R.drawable.taskpercent),
                    text = it.toInt().toString(),
                    contentDescription = "taskPercent",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            }
            shelfData.pressure?.let {
                RoundedIconButton(
                    painter = painterResource(R.drawable.pressure),
                    text = it.toInt().toString() + " Pa",
                    contentDescription = "pressure",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    onClick = {}
                )
            }
            RoundedIconButton(
                painter = painterResource(R.drawable.battery),
                text = shelfData.charge.toString() + "%",
                contentDescription = "battery",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                onClick = {}
            )
        }

        Spacer(Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            shelfData.warnings?.let {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    it.forEach() { data->
                        RoundedIconButton(
                            text = data.text,
                            contentDescription = "warning",
                            contentColor = data.errorType.textColor,
                            containerColor = data.errorType.containerColor,
                            borderColor = data.errorType.borderColor,
                            borderWidth = 1.dp,
                            onClick = {}
                        )
                    }

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (true) {
                        Icon(
                            tint = Color.Unspecified,
                            painter = painterResource(R.drawable.passtest),
                            contentDescription = "test success"
                        )
                    } else {
                        Icon(
                            tint = Color.Unspecified,
                            painter = painterResource(R.drawable.errortest),
                            contentDescription = "test success"
                        )
                    }
                    Text(
                        text = "15/05/2025 15:30"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.checktest),
                        contentDescription = "check test"
                    )

                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.startharvest),
                        contentDescription = "start harvest"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ShelfItemPreview() {
    ShelfScreen(
        ShelfData(
            id = 1,
            linked = false,
            humidity = 45.0,
            temperature = 22.5,
            pressure = 1013.2,
            charge = 85,
            taskPercent = 40.0
        ),
    )
}
