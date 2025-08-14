package com.example.mushroom.view.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
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

    if (selected) {
        tint = Color.Unspecified
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color("#E7AE8B".toColorInt()),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .background(
                color = Color("#F1D0BC".toColorInt()),
                shape = RoundedCornerShape(size = 20.dp)
            )
    } else {
        modifier = Modifier
    }

    Box(
        modifier = Modifier.clickable {
            onIconClick()
        }
    )
    {
        IconButton(
            modifier = modifier,
            onClick = onIconClick
        ) {
            Icon(
                tint = tint,
                painter = painterResource(R.drawable.shelf),
                contentDescription = "shelf"
            )
        }
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