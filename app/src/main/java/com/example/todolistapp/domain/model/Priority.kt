package com.example.todolistapp.domain.model

import androidx.compose.ui.graphics.Color

enum class Priority(val color: Color) {
    LOW(color = Color.Green),
    MEDIUM(color = Color.Yellow),
    HIGH(color = Color.Red)
}