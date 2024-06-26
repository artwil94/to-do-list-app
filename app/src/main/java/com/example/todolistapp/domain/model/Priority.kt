package com.example.todolistapp.domain.model

import androidx.compose.ui.graphics.Color
import com.example.todolistapp.ui.theme.TdColors

enum class Priority(val color: Color) {
    LOW(color = Color.Green),
    MEDIUM(color = TdColors().mediumPriority),
    HIGH(color = Color.Red)
}