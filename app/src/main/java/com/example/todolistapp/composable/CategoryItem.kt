package com.example.todolistapp.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.todolistapp.domain.model.Category
import com.example.todolistapp.ui.theme.ToDoTheme

@Composable
fun CategoryItem(category: Category) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = category.icon), contentDescription = category.name)
        Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingS))
        Text(
            text = category.name,
            style = ToDoTheme.tDTypography.taskTitle,
            modifier = Modifier.weight(8f)
        )
    }
}