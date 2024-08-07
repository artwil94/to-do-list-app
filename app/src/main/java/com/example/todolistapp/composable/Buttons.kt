package com.example.todolistapp.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.theme.ToDoTheme

@Preview
@Composable
fun ActionButtonPreview() {
    ActionButton(text = "Delete") {}
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    actionButtonType: ActionButtonType = ActionButtonType.Default,
    color: Color = ToDoTheme.tDColors.actionButton,
    inverted: Boolean = false,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onClick: () -> Unit,
) {
    val colors =
        ButtonDefaults.buttonColors(
            when (actionButtonType) {
                ActionButtonType.Default -> color
                ActionButtonType.WithBorder -> color
                else -> Color.White
            }
        )
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .height(40.dp)
            .then(modifier),
        border = BorderStroke(
            width = if (actionButtonType == ActionButtonType.WithBorder) 1.dp else 0.dp,
            color = Color.White
        ),
        colors = colors,
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 8.dp
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    tint = if (inverted) Color.White else ToDoTheme.tDColors.textStandard
                )
                Spacer(modifier = Modifier.width(ToDoTheme.tDDimensions.paddingXs))
            }
            Text(
                text = text.uppercase(),
                style = if (inverted) ToDoTheme.tDTypography.actionButtonWhite else ToDoTheme.tDTypography.actionButton
            )
            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    tint = if (inverted) Color.White else ToDoTheme.tDColors.textStandard
                )
            }
        }
    }
}

enum class ActionButtonType {
    Default,
    Cancel,
    WithBorder
}