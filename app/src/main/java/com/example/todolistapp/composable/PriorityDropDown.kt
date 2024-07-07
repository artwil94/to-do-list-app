package com.example.todolistapp.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolistapp.R
import com.example.todolistapp.domain.model.Category
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
            .rotate(if (expanded) 180f else 0f)
            .weight(1.5f), onClick = { expanded = !expanded }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_dropdown),
                contentDescription = stringResource(id = R.string.content_description_drop_down_arrow)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            DropdownMenuItem(
                onClick = {
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

@Composable
fun CategoryDropDown(category: Category, onCategoryClick: (Category) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable {
            expanded = !expanded
        }
        .border(width = 1.dp, color = Color.LightGray, shape = ToDoTheme.tDShapes.inputField),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = category.icon),
            contentDescription = category.name,
            modifier = Modifier.padding(start = ToDoTheme.tDDimensions.paddingS)
        )
        Text(
            text = category.name,
            style = ToDoTheme.tDTypography.taskTitle,
            modifier = Modifier.weight(8f)
        )
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingS))
        IconButton(modifier = Modifier
            .rotate(if (expanded) 180f else 0f)
            .weight(1.5f), onClick = { expanded = !expanded }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_dropdown),
                contentDescription = stringResource(id = R.string.content_description_drop_down_arrow)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onCategoryClick.invoke(Category.PERSONAL)
            }
            ) {
                CategoryItem(category = Category.PERSONAL)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onCategoryClick.invoke(Category.WORK)
            }
            ) {
                CategoryItem(category = Category.WORK)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onCategoryClick.invoke(Category.HOME)
            }
            ) {
                CategoryItem(category = Category.HOME)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                onCategoryClick.invoke(Category.SPORT)
            }
            ) {
                CategoryItem(category = Category.SPORT)
            }
        }
    }
}

