package com.example.mushroom.enums

import androidx.compose.ui.graphics.Color
import com.example.mushroom.view.icon.COLORS

enum class ErrorType(
    val containerColor: Color,
    val borderColor: Color,
    val textColor: Color
) {
    ERROR(
        containerColor = COLORS.ErrorContainer,
        borderColor = COLORS.ErrorBorder,
        textColor = COLORS.ErrorText
    ),
    WARNING(
        containerColor = COLORS.WarningContainer,
        borderColor = COLORS.WarningBorder,
        textColor = COLORS.WarningText
    ),
    INFO(
        containerColor = COLORS.InfoContainer,
        borderColor = COLORS.InfoBorder,
        textColor = COLORS.InfoText
    )
}

