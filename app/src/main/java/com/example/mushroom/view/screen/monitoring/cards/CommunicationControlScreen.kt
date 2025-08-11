package com.example.mushroom.view.screen.monitoring.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import com.example.mushroom.view.icon.COLORS
import com.example.mushroom.view.icon.RoundedIconButton
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ConnectionControlStatusScreen(
    id: Int,
    connectionStatus: Boolean,
    cameraConnectionStatus: Boolean,
    sensorStatus: Boolean,
    testingTime: LocalDateTime
) {
    ConnectionControlStatusContent(
        id,
        connectionStatus,
        cameraConnectionStatus,
        sensorStatus,
        testingTime
    )
}

@Composable
fun ConnectionControlStatusContent(
    id: Int,
    connectionStatus: Boolean,
    cameraConnectionStatus: Boolean,
    sensorStatus: Boolean,
    testingTime: LocalDateTime
) {
    val time = remember(testingTime) {
        testingTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault()))
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CONNECTION AND CONTROL",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = time,
                fontSize = 20.sp,
                color = Color("#7E7D72".toColorInt())
            )

            Spacer(modifier = Modifier.weight(1f))

            RoundedIconButton(
                painter = painterResource(R.drawable.testing),
                text = "Test",
                contentDescription = "testing begin",
                borderColor = COLORS.StatusTestingBorder,
                borderWidth = 1.dp,
                contentColor = COLORS.StatusTestingText,
                cornerSize = 25.dp,
                top = 0.dp,
                bottom = 0.dp,
                start = 15.dp,
                end = 15.dp
            ) { println("Testing begin for manipulator id $id") }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp),
            color = Color("#C7C5B4".toColorInt())

        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = Color.Black,
                        painter = painterResource(R.drawable.mini_test_hand),
                        contentDescription = "Manipulator"
                    )
                    Text(
                        text = " Manipulator: $id",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (connectionStatus) {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.success),
                        contentDescription = "connection status",
                        text = " Connected",
                        contentColor = COLORS.StatusSuccessText,
                        containerColor = COLORS.StatusSuccessContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 15.dp,
                        top = 2.dp,
                        end = 15.dp,
                        bottom = 2.dp,
                    )
                } else {
                    RoundedIconButton(
                        contentDescription = "connection status",
                        text = "No connection",
                        contentColor = COLORS.StatusErrorText,
                        containerColor = COLORS.StatusErrorContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp,
                        end = 10.dp,
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = Color.Black,
                        painter = painterResource(R.drawable.mini_test_camera),
                        contentDescription = "Cameras"
                    )
                    Text(
                        text = " Cameras",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (cameraConnectionStatus) {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.success),
                        contentDescription = "connection status",
                        text = " Connected",
                        contentColor = COLORS.StatusSuccessText,
                        containerColor = COLORS.StatusSuccessContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 15.dp,
                        top = 2.dp,
                        bottom = 2.dp,
                        end = 15.dp,
                    )
                } else {
                    RoundedIconButton(
                        contentDescription = "connection status",
                        text = "No connection with cameras",
                        contentColor = COLORS.StatusErrorText,
                        containerColor = COLORS.StatusErrorContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 10.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 10.dp,
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint = Color.Black,
                        painter = painterResource(R.drawable.mini_test_sensor),
                        contentDescription = "sensor"
                    )
                    Text(
                        text = " Sensors",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (sensorStatus) {
                    RoundedIconButton(
                        painter = painterResource(R.drawable.success),
                        contentDescription = "connection status",
                        text = " Connected",
                        contentColor = COLORS.StatusSuccessText,
                        containerColor = COLORS.StatusSuccessContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 15.dp,
                        top = 2.dp,
                        bottom = 2.dp,
                        end = 15.dp,
                    )
                } else {
                    RoundedIconButton(
                        contentDescription = "connection status",
                        text = "No connection",
                        contentColor = COLORS.StatusErrorText,
                        containerColor = COLORS.StatusErrorContainer,
                        cornerSize = 18.dp,
                        borderWidth = 0.dp,
                        start = 10.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 10.dp,
                    )
                }
            }
        }
    }
}


@Preview(name = "Tablet", device = TABLET, showSystemUi = true)
@Composable
fun ConnectionControlStatusPreview() {
    ConnectionControlStatusScreen(
        1,
        connectionStatus = true,
        cameraConnectionStatus = false,
        sensorStatus = true,
        LocalDateTime.now()
    )
}