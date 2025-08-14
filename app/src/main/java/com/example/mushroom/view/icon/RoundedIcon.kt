package com.example.mushroom.view.icon

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mushroom.R

@Composable
fun RoundedIconButton(
    painter: Painter? = null,
    text: String = "",
    textSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified,
    textFontWeight: FontWeight = FontWeight.Normal,
    iconSize: Dp = Dp.Unspecified,
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
    enabled: Boolean = true,
    pressAnimation: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(cornerSize)
    val interaction = remember { MutableInteractionSource() }
    val isPressed = if (onClick != null) {
        interaction.collectIsPressedAsState().value
    } else false

    // Subtle press animations (scale + shadow)
    val scale by animateFloatAsState(
        targetValue = if (pressAnimation && isPressed) 0.96f else 1f,
        label = "press-scale"
    )
    val elevation by animateDpAsState(
        targetValue = if (pressAnimation && isPressed) 2.dp else 0.dp,
        label = "press-elev"
    )

    val clickMod =
        if (onClick != null) {
            Modifier
                .clip(shape)
                .clickable(
                    enabled = enabled,
                    interactionSource = interaction,
                    indication = ripple(true),
                    onClick = onClick
                )
        } else Modifier

    Box(
        modifier = Modifier
            .clip(shape)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .background(color = containerColor, shape = RoundedCornerShape(size = cornerSize))
            .then(clickMod)
            .padding(start = start, top = top, end = end, bottom = bottom)
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
                    modifier = Modifier.size(iconSize)
                )
                Text(
                    text = text,
                    color = contentColor,
                    fontSize = textSize,
                    fontWeight = textFontWeight,
                    textAlign = textAlign
                )
            }
        } ?: run {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    color = contentColor,
                    fontSize = textSize,
                    fontWeight = textFontWeight,
                    textAlign = textAlign
                )
            }
        }
    }
}

@Preview
@Composable
fun RoundedIconButtonPreview() {
    RoundedIconButton(
        painter = painterResource(id = R.drawable.status_link),
        containerColor = ColorBlue, contentDescription = "status link", text = "123"
    )
}

val ColorBlue: Color = Color(0xFF599FF9)
val LightBlue: Color = Color(0xFFE0EDFE)
