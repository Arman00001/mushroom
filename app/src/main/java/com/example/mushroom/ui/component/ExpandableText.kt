package com.example.mushroom.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    text: String,
    minimizedMaxLines: Int = 4,
    style: TextStyle
) {
    var expanded by remember { mutableStateOf(false) }
    var isOverflowing by remember { mutableStateOf(false) }

    Column {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.animateContentSize(),
            onTextLayout = { textLayoutResult ->  
                if(!expanded){
                    isOverflowing = textLayoutResult.hasVisualOverflow
                }
            }
        )

        if(isOverflowing) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (expanded) "Read less" else "Read more",
                color = MaterialTheme.colorScheme.primary,
                style = style,
                modifier = Modifier.clickable { expanded = !expanded }
            )
        }
    }

}