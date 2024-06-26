package com.example.todolistapp.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistapp.domain.model.Priority
import com.example.todolistapp.ui.theme.ToDoTheme

@Composable
fun PriorityItem(priority: Priority) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(ToDoTheme.tDDimensions.priorityIndicator)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingS))
        Text(
            text = priority.name,
            style = ToDoTheme.tDTypography.taskTitle,
            modifier = Modifier.weight(8f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.LOW)
}