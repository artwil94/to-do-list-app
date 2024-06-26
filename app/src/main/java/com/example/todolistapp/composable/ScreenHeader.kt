package com.example.todolistapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistapp.ui.theme.ToDoTheme

@Preview
@Composable
fun ScreenHeaderPreview(){
    ScreenHeader("My tasks")
}

@Composable
fun ScreenHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToDoTheme.tDDimensions.screenHeaderHeight)
            .background(color = ToDoTheme.tDColors.screenHeader),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = ToDoTheme.tDTypography.screenHeader
        )
    }
}