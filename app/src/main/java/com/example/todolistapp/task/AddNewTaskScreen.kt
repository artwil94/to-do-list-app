package com.example.todolistapp.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolistapp.R
import com.example.todolistapp.composable.ActionButton
import com.example.todolistapp.composable.CategoryDropDown
import com.example.todolistapp.composable.ChangeSystemBarColor
import com.example.todolistapp.composable.InputFieldWithLabel
import com.example.todolistapp.composable.InputTopLabel
import com.example.todolistapp.composable.PriorityDropDown
import com.example.todolistapp.composable.TaskDescriptionInput
import com.example.todolistapp.composable.ToolBar
import com.example.todolistapp.data.Task
import com.example.todolistapp.home.TaskViewModel
import com.example.todolistapp.navigation.Screen
import com.example.todolistapp.ui.theme.ToDoTheme
import com.example.todolistapp.utils.Constants.TASK_DESCRIPTION_LENGTH_LIMIT

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    selectedTask: Task?,
    navController: NavHostController
) {
    ChangeSystemBarColor(statusBarColor = ToDoTheme.tDColors.screenHeader)
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = Color.White,
        topBar = {
            ToolBar(
                showTitle = true,
                onNavigationIconClick = { navController.popBackStack() },
                title = stringResource(id = R.string.add_new_task),
                withShadowing = true
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            val taskTitle by viewModel.title
            val taskDescription by viewModel.description
            val taskPriority by viewModel.priority
            val taskCategory by viewModel.category
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(ToDoTheme.tDDimensions.padding)
            ) {
                InputFieldWithLabel(
                    text = taskTitle,
                    label = stringResource(id = R.string.title),
                    onValueChange = {
                        viewModel.updateTitle(it)
                    })
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingXL))
                InputTopLabel(title = stringResource(id = R.string.priority))
                PriorityDropDown(
                    priority = taskPriority,
                    onPriority = { priority -> viewModel.priority.value = priority })
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingXL))
                InputTopLabel(title = stringResource(id = R.string.category))
                CategoryDropDown(
                    category = taskCategory,
                    onCategoryClick = { category -> viewModel.category.value = category })
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingXL))
                InputTopLabel(title = stringResource(id = R.string.description))
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingS))
                TaskDescriptionInput(
                    text = taskDescription,
                    onValueChange = { text: String ->
                        if (text.length <= TASK_DESCRIPTION_LENGTH_LIMIT) {
                            viewModel.description.value = text
                        }
                    })
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingXXL))
                ActionButton(
                    text = stringResource(id = R.string.save),
                    modifier = Modifier
                        .width(100.dp)
                        .align(Alignment.End),
                    inverted = true,
                    onClick = {
                        if(selectedTask != null) {
                            viewModel.updateTask()
                        } else {
                            viewModel.addTask()
                        }
                        navController.navigate(Screen.Home.route)
                    })
            }
        }
    }
}