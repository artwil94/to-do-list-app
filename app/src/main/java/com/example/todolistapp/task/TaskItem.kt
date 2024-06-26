package com.example.todolistapp.task

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistapp.R
import com.example.todolistapp.data.Task
import com.example.todolistapp.domain.model.Priority
import com.example.todolistapp.ui.theme.ToDoTheme

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TaskItemPreview() {
    TaskItem(Task(title = "Task", description = "Example Task", priority = Priority.MEDIUM))
}

@Composable
fun TaskItem(
    task: Task,
    isTaskDone: Boolean = false,
    onCheckboxClick: () -> Unit = {}
) {
//    val isTaskDone = rememberSaveable { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier.clickable {
//                isTaskDone.value = !isTaskDone.value
                onCheckboxClick.invoke()
            },
            painter = painterResource(
                id = if (isTaskDone) R.drawable.ic_checked else R.drawable.ic_unchecked
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.padding))
        Column {
            Text(
                text = task.title,
                style = ToDoTheme.tDTypography.taskTitle,
                textDecoration = if (isTaskDone) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingS))
            Text(text = task.description, style = ToDoTheme.tDTypography.taskDescription)
        }
        Spacer(modifier = Modifier.weight(8f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Canvas(modifier = Modifier.size(ToDoTheme.tDDimensions.priorityIndicator)) {
                drawCircle(color = task.priority?.color ?: Color.Transparent)
            }
        }
    }
}