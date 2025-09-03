package com.example.mushroom.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.wear.compose.material.Text
import com.example.mushroom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuSelected(
    options: List<Int> = listOf(1, 2, 3, 4, 5, 6),
    onItemClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("Select Shelves") }
    val shape = RoundedCornerShape(25.dp)
    val painter = painterResource(R.drawable.dropdown_1_arrow)
    val containerColor = Color("#EAE9DD".toColorInt())
    val contentColor = Color("#4D4C46".toColorInt())
    val borderColor = Color("#858378".toColorInt())
    val borderWidth = 1.dp
    val cornerSize = 30.dp
    val start = 18.dp
    val end = 18.dp
    val top = 15.dp
    val bottom = 15.dp

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Box(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                .clip(shape)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
                .background(color = containerColor, shape = RoundedCornerShape(size = cornerSize))
                .padding(start = start, end = end)//, top = top, bottom = bottom)
        ) {
            Row(
                modifier = Modifier.height(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    text = text,
                    color = contentColor,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    tint = contentColor,
                    painter = painter,
                    contentDescription = "",
                )
            }

        }
        ExposedDropdownMenu(
            expanded = expanded,
            containerColor = containerColor,
            border = BorderStroke(borderWidth, borderColor),
            shape = shape,
            onDismissRequest = { expanded = false }
        ) {

            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = "Shelf Nº$item", color = contentColor) },
                    onClick = {
                        onItemClick(item)
                        text = "Shelf Nº$item"
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun DropdownMenuPreview() {
    DropdownMenuSelected(
        onItemClick = { println(it) }
    )
}