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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.todolistapp.composable.ChangeSystemBarColor
import com.example.todolistapp.composable.InputFieldWithLabel
import com.example.todolistapp.composable.InputTopLabel
import com.example.todolistapp.composable.TaskDescriptionInput
import com.example.todolistapp.composable.ToolBar
import com.example.todolistapp.domain.model.TaskModel
import com.example.todolistapp.ui.theme.ToDoTheme

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun TaskScreen(
    viewModel: AddNewTaskViewModel = hiltViewModel(),
    navController: NavHostController
) {
    ChangeSystemBarColor(statusBarColor = ToDoTheme.tmColors.screenHeader)
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = Color.White,
        topBar = {
            ToolBar(
                showTitle = true,
                onNavigationIconClick = { navController.popBackStack() },
                title = "Add new task",
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
            var taskTitle by rememberSaveable { mutableStateOf("") }
            var taskDescription by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(ToDoTheme.tMDimensions.padding)
            ) {
                InputFieldWithLabel(
                    text = taskTitle,
                    label = stringResource(id = R.string.title),
                    onValueChange = { text: String ->
                        if (text.length <= 100) {
                            taskTitle = text
                        }
                    })
                Spacer(modifier = Modifier.height(ToDoTheme.tMDimensions.paddingXL))
                InputTopLabel(title = stringResource(id = R.string.description))
                Spacer(modifier = Modifier.height(ToDoTheme.tMDimensions.paddingS))
                TaskDescriptionInput(text = taskDescription,
                    onValueChange = { text: String ->
                        if (text.length <= 400) {
                            taskDescription = text
                        }
                    })
                Spacer(modifier = Modifier.height(ToDoTheme.tMDimensions.paddingXXL))
                ActionButton(
                    text = stringResource(id = R.string.save),
                    modifier = Modifier
                        .width(100.dp)
                        .align(Alignment.End),
                    inverted = true,
                    onClick = {
                        val task = TaskModel(
                            title = taskTitle,
                            description = taskDescription
                        )
                        viewModel.addTask(taskModel = task)
                    })
            }
        }
    }
}