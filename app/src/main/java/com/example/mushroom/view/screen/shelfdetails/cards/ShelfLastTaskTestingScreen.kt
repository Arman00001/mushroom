package com.example.mushroom.view.screen.shelfdetails.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
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

    var expanded by remember { mutableStateOf(false) }
    val maxWhenCollapsed = 3


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

        FlowRow(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!warnings.isNullOrEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.errortest),
                    tint = Color.Unspecified,
                    contentDescription = "collect",
                    modifier = Modifier.size(35.dp)
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.passtest),
                    tint = Color.Unspecified,
                    contentDescription = "collect",
                    modifier = Modifier.size(35.dp)
                )
            }

            RoundedIconButton(
                text = time,
                textAlign = TextAlign.Center,
                textFontWeight = FontWeight.W500,
                contentDescription = "time tested",
                start = 10.dp,
                end = 10.dp,
                textSize = 15.sp,
            )

            warnings?.let {
                if (warnings.isEmpty()) return@let

                val extraCount = (warnings.size - maxWhenCollapsed).coerceAtLeast(0)
                val visible =
                    if (expanded || extraCount == 0) warnings else warnings.take(maxWhenCollapsed)

                visible.forEach { data ->
                    RoundedIconButton(
                        text = data.text,
                        contentDescription = "warning",
                        contentColor = data.errorType.textColor,
                        containerColor = data.errorType.containerColor,
                        borderColor = data.errorType.borderColor,
                        borderWidth = 1.dp,
                        textSize = 16.sp,
                        textFontWeight = FontWeight.W500,
                        top = 15.dp,
                        bottom = 15.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                }

                if (extraCount > 0) {
                    if (!expanded) {
                        RoundedIconButton(
                            onClick = { expanded = true },
                            contentDescription = "expand",
                            painter = painterResource(R.drawable.dropdown),
                            containerColor = Color("#E2E9E5".toColorInt()),
                            borderWidth = (-1).dp,
                            top = 3.dp,
                            bottom = 3.dp,
                            start = 18.dp,
                            end = 18.dp
                        )
                    } else {
                        RoundedIconButton(
                            onClick = { expanded = false },
                            contentDescription = "expand",
                            painter = painterResource(R.drawable.dropdown),
                            containerColor = Color("#E2E9E5".toColorInt()),
                            borderWidth = (-1).dp,
                            modifier = Modifier.scale(scaleX = 1f, scaleY = -1f),
                            top = 3.dp,
                            bottom = 3.dp,
                            start = 18.dp,
                            end = 18.dp
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.padding(25.dp)) {
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