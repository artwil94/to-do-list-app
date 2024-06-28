package com.example.todolistapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.todolistapp.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
    @StringRes val contentDescription: Int? = null
) {
    companion object {
        private const val ROUTE_HOME = "home"
        const val ROUTE_TASK = "task"
        private const val ROUTE_SETTINGS = "settings"
        const val TASK_ID = "taskId"
    }

    data object Home : Screen(
        route = ROUTE_HOME,
        title = R.string.home,
        icon = R.drawable.ic_home,
        contentDescription = R.string.content_description_home_screen
    )

    data object Task : Screen(
        route = "$ROUTE_TASK/{$TASK_ID}",
        title = R.string.task,
        icon = R.drawable.ic_note,
        contentDescription = R.string.content_description_new_task
    ) {
        fun route(taskId: String?): String =
            route.replace(oldValue = "{$TASK_ID}", newValue = taskId ?: "-1")
    }

    data object Settings : Screen(
        route = ROUTE_SETTINGS,
        title = R.string.settings,
        icon = R.drawable.ic_settings,
        contentDescription = R.string.content_description_settings
    )

}