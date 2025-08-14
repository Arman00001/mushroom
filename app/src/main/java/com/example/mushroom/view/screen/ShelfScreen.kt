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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.icon.VariablesError
import com.example.mushroom.view.screen.data.ShelfData
import com.example.mushroom.viewmodel.ShelfViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShelfScreen(
    shelfData: ShelfData,
    viewModel: ShelfViewModel = koinViewModel(),
    onMonitoringClick: (Int) -> Unit
) {
    ShelfContent(
        shelfData = shelfData,
        onOpenCloseClick = viewModel.getShelfOpenCloseClickMethod(shelfData.warnings),
        onMonitoringClick = onMonitoringClick,
    )
}

@Composable
fun ShelfContent(
    shelfData: ShelfData,
    onOpenCloseClick: (Int) -> Unit,
    onMonitoringClick: (Int) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shelf N˚" + shelfData.id,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = "Manipulator ID: ${shelfData.id}",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color("#7E7D72".toColorInt())
            )

            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = { onMonitoringClick(shelfData.id) }) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(R.drawable.safe),
                        contentDescription = "safe"
                    )
                }

                var painter = R.drawable.close
                if (shelfData.warnings == null) {
                    painter = R.drawable.success
                }
                IconButton(onClick = { onOpenCloseClick(shelfData.id) }) {
                    Icon(
                        tint = Color.Unspecified,
                        painter = painterResource(painter),
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
                )
            } else {
                RoundedIconButton(
                    painter = painterResource(R.drawable.status_link_error),
                    contentDescription = "link status",
                    contentColor = Color.Red,
                    containerColor = VariablesError.statusesVErrorContainer,
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                )
            }
            if (shelfData.humidity < 60) {
                RoundedIconButton(
                    painter = painterResource(R.drawable.humidity),
                    text = shelfData.humidity.toInt().toString() + " %",
                    contentDescription = "humidity",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                )
            } else {
                RoundedIconButton(
                    painter = painterResource(R.drawable.humidity),
                    text = shelfData.humidity.toInt().toString() + " %",
                    contentDescription = "humidity",
                    contentColor = Color.Red,
                    containerColor = VariablesError.statusesVErrorContainer,
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                )
            }
            shelfData.temperature?.let {
                if (it < 25) {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.celsius),
                        text = " " + it.toInt().toString(),
                        contentDescription = "temperature",
                        borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    )
                } else {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.celsius),
                        text = " " + it.toInt().toString(),
                        contentDescription = "temperature",
                        contentColor = Color.Red,
                        containerColor = VariablesError.statusesVErrorContainer,
                        borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                    )
                }
            }
            shelfData.taskPercent?.let {
                RoundedIconButton(
                    painter = painterResource(R.drawable.taskpercent),
                    text = it.toInt().toString(),
                    contentDescription = "taskPercent",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                )
            }
            shelfData.pressure?.let {
                RoundedIconButton(
                    painter = painterResource(R.drawable.pressure),
                    text = it.toInt().toString() + " Pa",
                    contentDescription = "pressure",
                    borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
                )
            }
            RoundedIconButton(
                painter = painterResource(R.drawable.battery),
                text = shelfData.charge.toString() + "%",
                contentDescription = "battery",
                borderColor = VariablesError.SurfaceVarsSurfaceContainerHighest,
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
                    it.forEach { data ->
                        RoundedIconButton(
                            text = data.text,
                            contentDescription = "warning",
                            contentColor = data.errorType.textColor,
                            containerColor = data.errorType.containerColor,
                            borderColor = data.errorType.borderColor,
                            borderWidth = 1.dp,
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
                    if (shelfData.warnings == null) {
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

@Preview(device = Devices.TABLET, showSystemUi = true)
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
        onMonitoringClick = { println("monitoring click") }
    )
}
