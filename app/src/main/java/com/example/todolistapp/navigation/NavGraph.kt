package com.example.todolistapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolistapp.home.HomeScreen
import com.example.todolistapp.home.TaskViewModel
import com.example.todolistapp.navigation.Screen.Companion.TASK_ID
import com.example.todolistapp.settings.SettingsScreen
import com.example.todolistapp.task.TaskScreen


@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Task.route,
            arguments = listOf(navArgument(TASK_ID) {
                type = NavType.StringType
            }),
        ) { backStackEntry ->
            val taskId = (backStackEntry.arguments?.getString(TASK_ID) ?: "").toInt()
            viewModel.getTaskDetails(taskId = taskId)
            val selectedTask by viewModel.selectedTask.collectAsState()
            TaskScreen(
                navController = navController,
                selectedTask = selectedTask
//                taskId = backStackEntry.arguments?.getString(TASK_ID) ?: ""
            )
        }
        composable(
            route = Screen.Settings.route,
        ) {
            SettingsScreen(navController = navController)
        }
    }
}