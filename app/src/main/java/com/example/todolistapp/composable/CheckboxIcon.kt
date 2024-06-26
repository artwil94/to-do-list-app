package com.example.todolistapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todolistapp.R

@Composable
fun CheckBoxIcon(
    modifier: Modifier = Modifier,
    isDone: Boolean = false,
    onCheckClick: () -> Unit = {},
    size: Dp = 20.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onCheckClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.5f),
            painter = painterResource(id = if (isDone) R.drawable.ic_checked else R.drawable.ic_unchecked),
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}