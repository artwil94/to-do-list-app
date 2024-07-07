package com.example.todolistapp.task

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.R
import com.example.todolistapp.data.Task
import com.example.todolistapp.domain.model.Priority
import com.example.todolistapp.ui.theme.ToDoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TaskItemPreview() {
    TaskItem(Task(title = "Task", description = "Example Task", priority = Priority.MEDIUM))
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TaskItem(
    task: Task,
    onCheckboxClick: () -> Unit = {},
    onDeleteClick: (Task) -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    val isTaskDone = rememberSaveable { mutableStateOf(false) }
    var showPriorityLabel by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Row(modifier = Modifier.fillMaxWidth() ) {
        Icon(
            modifier = Modifier.clickable {
                isTaskDone.value = !isTaskDone.value
                onCheckboxClick.invoke()
            },
            painter = painterResource(
                id = if (isTaskDone.value) R.drawable.ic_checked else R.drawable.ic_unchecked
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.padding))
        Column(modifier = Modifier.width(250.dp)) {
            Text(
                text = task.title,
                style = ToDoTheme.tDTypography.taskTitle,
                textDecoration = if (isTaskDone.value) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
            if (task.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingS))
                Text(text = task.description, style = ToDoTheme.tDTypography.taskDescription)
            }
        }
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingS))
//        task.category?.icon?.let { painterResource(id = it) }?.let {
//            Icon(
//                painter = it,
//                contentDescription = task.category.name,
//                modifier = Modifier.size(ToDoTheme.tDDimensions.editTaskIcon)
//            )
//        }
//        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column {
                Box(contentAlignment = Alignment.Center) {
                    Canvas(
                        modifier = Modifier
                            .size(
                                if (showPriorityLabel) ToDoTheme.tDDimensions.priorityIndicatorMin else
                                    ToDoTheme.tDDimensions.priorityIndicator
                            )
                            .clickable {
                                showPriorityLabel = true
                            }
                    ) {
                        drawCircle(color = task.priority?.color ?: Color.Transparent)
                    }
                    if (showPriorityLabel) {
                        coroutineScope.launch {
                            delay(1000)
                            showPriorityLabel = false
                        }
                        Column(modifier = Modifier.align(Alignment.TopCenter)) {
                            Text(
                                text = (task.priority?.name ?: "").lowercase()
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                                style = ToDoTheme.tDTypography.priorityLabel
                            )
                            Text(
                                text = stringResource(id = R.string.priority),
                                style = ToDoTheme.tDTypography.priorityLabel
                            )
                        }
                    }
                }
            }
        }
        Icon(
            modifier = Modifier
                .size(ToDoTheme.tDDimensions.editTaskIcon)
                .weight(1f)
                .padding(end = ToDoTheme.tDDimensions.paddingS)
                .clickable {
                    onDeleteClick.invoke(task)
                }, imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.content_description_delete_task)
        )
    }
}