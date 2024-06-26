package com.example.todolistapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.ui.theme.ToDoTheme


@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Task,
        Screen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        modifier = Modifier
            .padding(top = ToDoTheme.tDDimensions.paddingXs)
            .clip(ToDoTheme.tDShapes.tabShape)
            .background(
                color = if (selected) ToDoTheme.tDColors.bottomBar else Color.White,
                shape = if (selected) ToDoTheme.tDShapes.tabShape else RoundedCornerShape(0.dp)
            ),
        selected = selected,
        onClick = { navController.navigate(screen.route) },
        icon = {
            val icon = screen.icon
            if (icon != null) {
                Icon(
                    modifier = Modifier.padding(),
                    painter = painterResource(id = screen.icon),
                    contentDescription = stringResource(id = screen.contentDescription!!),
                    tint = if (selected) Color.White else Color.Black
                )
            }
        },
        label = {
            Text(
                text = screen.title?.let { stringResource(id = it) } ?: "",
                style = if (selected) ToDoTheme.tDTypography.bottomBarLabelSelect
                else ToDoTheme.tDTypography.bottomBarLabelUnSelect
            )
        },
        selectedContentColor = ToDoTheme.tDColors.bottomBar,
        unselectedContentColor = Color.White
    )
}