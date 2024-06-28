package com.example.todolistapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolistapp.R
import com.example.todolistapp.composable.ActionButton
import com.example.todolistapp.composable.ActionButtonType
import com.example.todolistapp.composable.ChangeSystemBarColor
import com.example.todolistapp.composable.ConfirmationDialog
import com.example.todolistapp.composable.ScreenHeader
import com.example.todolistapp.data.Task
import com.example.todolistapp.navigation.BottomBar
import com.example.todolistapp.navigation.Screen
import com.example.todolistapp.task.TaskItem
import com.example.todolistapp.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navController: NavHostController
) {
    ChangeSystemBarColor(statusBarColor = ToDoTheme.tDColors.screenHeader)
    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }
    val allTasks by viewModel.allTasks.collectAsState()
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }
    val selectedTask = remember { mutableStateOf<Task?>(null) }
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = ToDoTheme.tDColors.backgroundScreen,
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            ScreenHeader(stringResource(id = R.string.my_tasks))
            Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
            Row(
                modifier = Modifier.padding(
                    start = ToDoTheme.tDDimensions.paddingS,
                    end = ToDoTheme.tDDimensions.paddingXL,
                    top = ToDoTheme.tDDimensions.paddingS
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    text = stringResource(id = R.string.add_new),
                    color = ToDoTheme.tDColors.bottomBar,
                    inverted = true,
                    leadingIcon = R.drawable.ic_add_item,
                    actionButtonType = ActionButtonType.WithBorder,
                    onClick = { navController.navigate(Screen.Task.route(taskId = null)) },
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter), contentDescription = "",
                    tint = ToDoTheme.tDColors.textStandard
                )
            }
            Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
            HorizontalDivider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ToDoTheme.tDColors.backgroundScreen)
                    .padding(top = ToDoTheme.tDDimensions.padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = ToDoTheme.tDDimensions.padding,
                            end = ToDoTheme.tDDimensions.padding,
                            top = ToDoTheme.tDDimensions.paddingL
                        )
                ) {
                    items(allTasks, key = { task -> task.id }) { task ->
                        TaskItem(
                            task = task,
                            onCheckboxClick = {
                                //TODO
                            },
                            onDeleteClick = {
                                showDeleteConfirmDialog = true
                                selectedTask.value = task
                            },
                            onEditClick = {
                                navController.navigate(Screen.Task.route(taskId = task.id.toString()))
                                viewModel.updateTask()
                            }
                        )
                        Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
                    }
                    item {
                        Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
                    }
                }
            }
        }
    }
    if (showDeleteConfirmDialog) {
        ConfirmationDialog(
            title = stringResource(id = R.string.confirmation_dialog_are_you_sure_to_delete_task),
            confirmText = stringResource(id = R.string.confirmation_dialog_remove),
            cancelText = stringResource(id = R.string.confirmation_dialog_cancel),
            onConfirm = {
                selectedTask.value?.let { viewModel.deleteTask(task = it) }
                showDeleteConfirmDialog = false
            },
            onCancel = {
                showDeleteConfirmDialog = false
            }
        )
    }
}