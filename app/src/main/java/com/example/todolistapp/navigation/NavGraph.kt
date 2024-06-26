package com.example.todolistapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolistapp.home.HomeScreen
import com.example.todolistapp.settings.SettingsScreen
import com.example.todolistapp.task.TaskScreen


@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Task.route
        ) {
            TaskScreen(navController = navController)
        }
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(navController = navController)
        }
    }
}