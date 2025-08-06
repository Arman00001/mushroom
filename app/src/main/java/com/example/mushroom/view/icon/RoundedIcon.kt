package com.example.mushroom.view.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mushroom.R

@Composable
fun RoundedIconButton(
    painter: Painter? = null,
    text: String = "",
    contentDescription: String,
    contentColor: Color = Color.Black,
    containerColor: Color = Color.White,
    borderColor: Color = VariablesError.SurfaceVarsSurfaceContainerHighest,
    borderWidth: Dp = 2.dp,
    start: Dp = 6.dp,
    top: Dp = 8.dp,
    end: Dp = 6.dp,
    bottom: Dp = 8.dp,
    cornerSize: Dp = 8.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(size = cornerSize)
            )
            .background(color = containerColor, shape = RoundedCornerShape(size = 8.dp))
            .padding(start = 6.dp, top = 8.dp, end = 6.dp, bottom = 8.dp)
    ) {
        painter?.let {
            Row(
                modifier = Modifier.height(44.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = contentColor,
                    painter = painter,
                    contentDescription = contentDescription,
                )
                Text(
                    text = text,
                    color = contentColor
                )
            }
        } ?: run {
            Text(
                text = text,
                color = contentColor
            )
        }
    }
}
@Preview
@Composable
fun RoundedIconButtonPreview() {
    RoundedIconButton(
        painter = painterResource(id = R.drawable.status_link),
//        loading = true,
        containerColor = ColorBlue, onClick = {}, contentDescription = "status link", text = "123"
    )
}

val ColorBlue: Color = Color(0xFF599FF9)
val LightBlue: Color = Color(0xFFE0EDFE)
