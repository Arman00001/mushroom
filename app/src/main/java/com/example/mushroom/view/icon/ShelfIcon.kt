package com.example.mushroom.view.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.mushroom.R

@Composable
fun ShelfIcon(
    number: Int,
    selected: Boolean,
    onIconClick: () -> Unit
) {
    var tint = Color("#616058".toColorInt())
    val modifier: Modifier
    val shape = RoundedCornerShape(size = 16.dp)
    val interaction = remember { MutableInteractionSource() }

    if (selected) {
        tint = Color.Unspecified
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color("#E7AE8B".toColorInt()),
                shape = shape
            )
            .background(
                color = Color("#F1D0BC".toColorInt()),
                shape = shape
            )
    } else {
        modifier = Modifier
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .clickable(
                onClick = onIconClick,
                interactionSource = interaction,
                indication = ripple(true),
            ),
    )
    {
        Icon(
            modifier = modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
            tint = tint,
            painter = painterResource(R.drawable.shelf),
            contentDescription = "shelf"
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp, y = (-10).dp)            // fine-tune if needed
                .defaultMinSize(minWidth = 18.dp, minHeight = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = number.toString(),
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}