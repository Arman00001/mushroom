package com.example.mushroom.view.screen.data

data class ShelfData(
    val id: Int,
    val linked: Boolean,
    val humidity: Double,
    val temperature: Double? = null,
    val taskPercent: Double? = null,
    val pressure: Double? = null,
    val charge: Int,
    val warnings: List<WarningData>? = null
)