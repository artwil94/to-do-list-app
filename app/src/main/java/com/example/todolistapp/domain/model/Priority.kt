package com.example.todolistapp.domain.model

import androidx.compose.ui.graphics.Color
import com.example.todolistapp.ui.theme.TdColors

enum class Priority(val color: Color, val priorityValue: Int) {
    LOW(color = Color.Green, priorityValue = 1),
    MEDIUM(color = TdColors().mediumPriority, priorityValue = 2),
    HIGH(color = Color.Red, priorityValue = 3)
}