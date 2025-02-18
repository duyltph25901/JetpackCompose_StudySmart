package com.virgo.duylt.education.study.smart.utils

import androidx.compose.ui.graphics.Color
import com.virgo.duylt.education.study.smart.presentation.theme.Green
import com.virgo.duylt.education.study.smart.presentation.theme.Orange
import com.virgo.duylt.education.study.smart.presentation.theme.Red

enum class Priority(
    val title: String = "",
    val color: Color = Color.Red,
    val value: Int = 0
) {
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.value == value} ?: MEDIUM
    }
}
