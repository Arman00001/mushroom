package com.example.mushroom.view.screen.shelfdetails.cards

import androidx.compose.foundation.layout.Arrangement
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
import androidx.core.graphics.toColorInt
import com.example.mushroom.R
import com.example.mushroom.view.icon.RoundedIconButton
import com.example.mushroom.view.screen.data.WarningData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ShelfLastTaskCollectScreen(
    mushroomHeightLowerRange: Int,
    mushroomHeightUpperRange: Int,
    mushroomCount: Int,
    collectSpeed: Int,
    timeCollected: LocalDateTime,
    warnings: List<WarningData>? = null
) {
    ShelfLastTaskCollectContent(
        mushroomHeightLowerRange = mushroomHeightLowerRange,
        mushroomHeightUpperRange = mushroomHeightUpperRange,
        mushroomCount = mushroomCount,
        collectSpeed = collectSpeed,
        timeCollected = timeCollected,
        warnings = warnings
    )
}

@Composable
fun ShelfLastTaskCollectContent(
    mushroomHeightLowerRange: Int,
    mushroomHeightUpperRange: Int,
    mushroomCount: Int,
    collectSpeed: Int,
    timeCollected: LocalDateTime,
    warnings: List<WarningData>?
) {
    val time = remember(timeCollected) {
        timeCollected.format(DateTimeFormatter.ofPattern("dd/MM/yyyy\nHH:mm", Locale.getDefault()))
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
                text = "Last task – Collect",
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
                painter = painterResource(R.drawable.mini_test_hand),
                tint = Color("#7E7D72".toColorInt()),
                contentDescription = "collect",
                modifier = Modifier.size(35.dp)
            )

            RoundedIconButton(
                text = time,
                textAlign = TextAlign.Center,
                contentDescription = "time collected",
                bottom = 2.dp
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.mushroom),
                text = "${mushroomHeightLowerRange}-${mushroomHeightUpperRange}cm",
                contentDescription = "mushroom height range"
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.mushrooms),
                text = "$mushroomCount",
                contentDescription = "collected mushroom count"
            )

            RoundedIconButton(
                painter = painterResource(R.drawable.speed),
                text = "$collectSpeed",
                contentDescription = "collect speed"
            )
        }

        warnings?.let {

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
    }
}