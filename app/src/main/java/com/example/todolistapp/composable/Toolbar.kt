package com.example.todolistapp.composable

import com.example.todolistapp.R
import com.example.todolistapp.ui.theme.ToDoTheme
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun ToolBar(
    showTitle: Boolean = true,
    title: String? = null,
    onNavigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = Color.White,
    withShadowing: Boolean = false,
    showBackNavigation: Boolean = true,
    backgroundTextColor: Color = Color.Transparent,
    @StringRes contentDescription: Int? = null
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(
            elevation = if (withShadowing) ToDoTheme.tMDimensions.paddingXL else 0.dp,
            spotColor = Color(0x0D000000)
        ),
        title = {
            if (showTitle) {
                Box(modifier = Modifier.padding(start = ToDoTheme.tMDimensions.paddingS)) {
                    Text(
                        modifier = Modifier.background(color = backgroundTextColor),
                        text = title ?: "",
                        style = ToDoTheme.tDTypography.toolbar,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        },
        navigationIcon = {
            if (showBackNavigation) {
                Box(
                    modifier = Modifier.padding(
                        start = ToDoTheme.tMDimensions.padding,
                        end = ToDoTheme.tMDimensions.padding
                    )
                ) {
                    Icon(
                        modifier = Modifier.clickable { onNavigationIconClick.invoke() },
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = contentDescription?.let { stringResource(id = it) },
                        tint = Color.Unspecified
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            scrolledContainerColor = backgroundColor,
            navigationIconContentColor = backgroundColor,
            titleContentColor = backgroundColor,
            actionIconContentColor = backgroundColor
        ),
    )
}