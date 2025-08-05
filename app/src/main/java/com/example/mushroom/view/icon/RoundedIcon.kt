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
    horizontalPadding: Dp = 8.dp,
    verticalPadding: Dp = 6.dp,
    cornerSize: Dp = 8.dp,
    onClick: ()->Unit
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
                Text(text = text, color = contentColor)
            }
        } ?: run {
            Text(
                text = text
            )
        }
    }
}

fun redModifier(): Modifier {
    return Modifier
        .border(
            width = 2.dp,
            color = VariablesError.SurfaceVarsSurfaceContainerHighest,
            shape = RoundedCornerShape(size = 8.dp)
        )
        .background(
            color = VariablesError.statusesVErrorContainer,
            shape = RoundedCornerShape(size = 8.dp)
        )
        .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
}

fun commonModifier(): Modifier {
    return Modifier
        .border(
            width = 2.dp,
            color = VariablesLinked.SurfaceVarsSurfaceContainerHighest,
            shape = RoundedCornerShape(size = 8.dp)
        )
        .padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)

}

@Preview
@Composable
fun RoundedIconButtonPreview() {
    RoundedIconButton(
        painter = painterResource(id = R.drawable.status_link),
//        loading = true,
        containerColor = ColorBlue,
        onClick = {},
        contentDescription = "status link",
        text = "123"
    )
}

val ColorBlue: Color = Color(0xFF599FF9)
val LightBlue: Color = Color(0xFFE0EDFE)


//@Composable
//fun HumidityCard(
//    modifier: Modifier = Modifier,
//    icon: Painter? = null,
//    valueText: String? = null,
//    iconTint: Color = Color(0xFFE53935),
//    backgroundColor: Color = Color(0xFFFFEBEB),
//    textColor: Color = Color(0xFFE53935),
//    cornerRadius: Dp = 16.dp,
//    textSize: TextUnit = 22.sp,
//    iconSize: Dp = 28.dp,
//    horizontalPadding: Dp = 20.dp,
//    verticalPadding: Dp = 12.dp,
//    spaceBetween: Dp = 8.dp
//) {
//    Box(
//        modifier = modifier
//            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
//            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            if (icon != null) {
//                Icon(
//                    painter = icon,
//                    contentDescription = null,
//                    tint = iconTint,
//                    modifier = Modifier.size(iconSize)
//                )
//            }
//
//            if (icon != null && valueText != null) {
//                Spacer(modifier = Modifier.width(spaceBetween))
//            }
//
//            if (valueText != null) {
//                Text(
//                    text = valueText,
//                    color = textColor,
//                    fontSize = textSize,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//    }
//}