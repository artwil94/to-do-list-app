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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.todolistapp.R
import com.example.todolistapp.composable.ActionButton
import com.example.todolistapp.composable.ChangeSystemBarColor
import com.example.todolistapp.composable.ScreenHeader
import com.example.todolistapp.navigation.BottomBar
import com.example.todolistapp.navigation.Screen
import com.example.todolistapp.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    ChangeSystemBarColor(statusBarColor = ToDoTheme.tmColors.screenHeader)
    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = ToDoTheme.tmColors.backgroundScreen,
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            ScreenHeader(stringResource(id = R.string.my_tasks))
            Spacer(modifier = Modifier.height(ToDoTheme.tMDimensions.padding))
            Row(
                modifier = Modifier.padding(
                    start = ToDoTheme.tMDimensions.paddingS,
                    end = ToDoTheme.tMDimensions.paddingXL,
                    top = ToDoTheme.tMDimensions.paddingS
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    text = stringResource(id = R.string.add_new),
                    color = ToDoTheme.tmColors.backgroundScreen,
                    leadingIcon = R.drawable.ic_add_item,
                    onClick = { navController.navigate(Screen.Task.route) })
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter), contentDescription = "",
                    tint = ToDoTheme.tmColors.textStandard
                )
            }
            Divider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ToDoTheme.tmColors.backgroundScreen)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = ToDoTheme.tMDimensions.padding,
                            end = ToDoTheme.tMDimensions.padding,
                            top = ToDoTheme.tMDimensions.paddingL
                        )
                ) {
                    //TODO Add items

                    item {
                        Spacer(modifier = Modifier.height(ToDoTheme.tMDimensions.padding))
                    }
                }
            }
        }
    }
}