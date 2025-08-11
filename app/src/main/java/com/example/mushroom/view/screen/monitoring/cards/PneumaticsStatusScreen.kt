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
fun PneumaticsStatusScreen(
    connectionStatus: Boolean,
    pressure: Int,
    testingTime: LocalDateTime,
) {
    PneumaticsStatusContent(
        connectionStatus = connectionStatus,
        pressure = pressure,
        testingTime = testingTime
    )
}

@Composable
fun PneumaticsStatusContent(
    connectionStatus: Boolean,
    pressure: Int,
    testingTime: LocalDateTime,
){
    val time = remember(testingTime) {
        testingTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault()))
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PNEUMATICS",
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
                top = 2.dp,
                bottom = 2.dp,
                start = 15.dp,
                end = 15.dp
            ) { println("Testing begin for manipulator") }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp),
            color = Color("#C7C5B4".toColorInt())

        )
        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color.Black,
                    painter = painterResource(R.drawable.pressure),
                    contentDescription = "pressure"
                )
                Text(
                    text = "Pressure",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            if (connectionStatus) {
                RoundedIconButton(
                    contentDescription = "connection status",
                    text = "$pressure Pa",
                    contentColor = COLORS.StatusSuccessText,
                    containerColor = COLORS.StatusSuccessContainer,
                    textSize = 25.sp,
                    cornerSize = 18.dp,
                    borderWidth = 0.dp,
                    start = 30.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 30.dp,
                )
            } else {
                RoundedIconButton(
                    contentDescription = "connection status",
                    text = "No connection with pneumatics",
                    contentColor = COLORS.StatusErrorText,
                    containerColor = COLORS.StatusErrorContainer,
                    cornerSize = 20.dp,
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

@Preview(name = "Tablet", device = TABLET, showSystemUi = true)
@Composable
fun PneumaticsStatusPreview(){
    PneumaticsStatusScreen(
        connectionStatus = true,
        pressure = 60,
        testingTime = LocalDateTime.now()
    )
}