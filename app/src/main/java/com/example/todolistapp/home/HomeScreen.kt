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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolistapp.R
import com.example.todolistapp.composable.ActionButton
import com.example.todolistapp.composable.ChangeSystemBarColor
import com.example.todolistapp.composable.ScreenHeader
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
//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    val tasks by produceState<List<TaskModel>>(
//        initialValue = emptyList(),
//        key1 = lifecycle,
//        key2 = viewModel
//    ) {
//        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
//            viewModel.taskState.collect { value = it }
//        }
//    }
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
                    color = Color.Yellow,
                    leadingIcon = R.drawable.ic_add_item,
                    onClick = { navController.navigate(Screen.Task.route) },
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
//                    items(allTasks, key = { task -> task.id }) { task ->
//                        TaskItem(
//                            taskModel = task,
////                            isTaskDone = uiState.isDone,
//                            onCheckboxClick = {
//                                viewModel.onDoneClick()
//                            })
                   items(allTasks, key = { task -> task.id}) {task->
                       TaskItem(
                           task = task,
//                            isTaskDone = uiState.isDone,
                           onCheckboxClick = {
//                               viewModel.onDoneClick()
                           })
                       Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
                   }
                        item {
                            Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.padding))
                        }
                    }
                }
            }
        }
    }