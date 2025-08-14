package com.example.mushroom.view.screen.shelfdetails.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mushroom.R
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.screen.StepProgressIndicator
import com.example.mushroom.view.screen.data.WarningData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ShelfLastTaskTestingScreen(
    timeTested: LocalDateTime,
    warnings: List<WarningData>? = null
) {
    ShelfLastTaskTestingContent(timeTested, warnings)
}

@Composable
fun ShelfLastTaskTestingContent(
    timeTested: LocalDateTime,
    warnings: List<WarningData>?
) {
    val time = remember(timeTested) {
        timeTested.format(DateTimeFormatter.ofPattern("dd/MM/yyyy\nHH:mm", Locale.getDefault()))
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Last task – Testing",
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
            Icon(
                painter = painterResource(R.drawable.errortest),
                tint = Color.Unspecified,
                contentDescription = "collect",
                modifier = Modifier.size(35.dp)
            )

            RoundedIconButton(
                text = time,
                textAlign = TextAlign.Center,
                contentDescription = "time tested",
                bottom = 2.dp
            )

            warnings?.let {

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

        Box(modifier = Modifier.padding(12.dp)) {
            StepProgressIndicator(
                painters = listOf(
                    painterResource(R.drawable.mini_test_hand),
                    painterResource(R.drawable.mini_test_camera),
                    painterResource(R.drawable.mini_test_sensor),
                    painterResource(R.drawable.pressure),
                    painterResource(R.drawable.battery)
                ),
                currentStep = 4
            )
        }
    }
}