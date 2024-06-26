package com.example.todolistapp.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolistapp.R
import com.example.todolistapp.domain.model.Priority
import com.example.todolistapp.ui.theme.ToDoTheme

@Composable
fun PriorityDropDown(priority: Priority, onPriority: (Priority) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable {
            expanded = !expanded
        }
        .border(width = 1.dp, color = Color.LightGray, shape = ToDoTheme.tDShapes.inputField),
        verticalAlignment = Alignment.CenterVertically) {
        Canvas(
            modifier = Modifier
                .size(ToDoTheme.tDDimensions.priorityIndicator)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            style = ToDoTheme.tDTypography.taskTitle,
            modifier = Modifier.weight(8f)
        )
        IconButton(modifier = Modifier
            .rotate(0f)
            .weight(1.5f), onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.content_description_drop_down_arrow)
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                expanded = false
                onPriority.invoke(Priority.LOW)
            }
            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onPriority.invoke(Priority.MEDIUM)
            }
            ) {
                PriorityItem(priority = Priority.MEDIUM)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onPriority.invoke(Priority.HIGH)
            }
            ) {
                PriorityItem(priority = Priority.HIGH)
            }

        }

    }


}