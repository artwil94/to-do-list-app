package com.example.todolistapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.theme.ToDoTheme

@Composable
fun NewActionSnackBar() {
    Box(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = ToDoTheme.tDShapes.tabShape
            )
            .width(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "New Task Added",
            style = ToDoTheme.tDTypography.taskTitle,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Preview
@Composable
fun NewActionSnackBarPreview() {
    NewActionSnackBar()
}