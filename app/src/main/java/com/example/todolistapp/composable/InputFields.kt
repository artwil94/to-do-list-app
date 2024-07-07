package com.example.todolistapp.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.theme.ToDoTheme

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun InputFieldWithLabelPreview() {
    InputFieldWithLabel(text = "Hire Artur", label = "Title")
}

@ExperimentalComposeUiApi
@Composable
fun TaskDescriptionInput(text: String, onValueChange: (String) -> Unit = {}) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = ToDoTheme.tDShapes.inputField
            )
            .padding(ToDoTheme.tDDimensions.paddingS),
        value = text,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
        }),
        maxLines = Int.MAX_VALUE
    )
}

@Composable
fun InputFieldWithLabel(
    text: String,
    label: String,
    onValueChange: (String) -> Unit = {},
    inputFieldType: InputFieldType = InputFieldType.Default,
    onClick: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
    placeHolder: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var expanded by remember { mutableStateOf(false) }
        InputTopLabel(title = label)
        Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingXs))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = ToDoTheme.tDShapes.inputField)
                .border(width = 0.dp, color = Color.Black, shape = ToDoTheme.tDShapes.inputField)
                .height(50.dp)
                .clickable {
                    if (inputFieldType == InputFieldType.Disabled) {
                        onClick.invoke()
                        expanded = !expanded
                    }
                },
            value = text,
            onValueChange = onValueChange,
            textStyle = ToDoTheme.tDTypography.inputField,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            ),
            singleLine = true,
            readOnly = inputFieldType == InputFieldType.Disabled,
            enabled = inputFieldType != InputFieldType.Disabled,
            trailingIcon = {
                trailingIcon?.let {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.rotate(if (expanded) 180f else 0f)
                    )
                }
            },
            placeholder = {
                placeHolder?.let {
                    Text(text = it, style = ToDoTheme.tDTypography.inputField)
                }
            }
        )
    }
}

@Composable
fun InputTopLabel(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(
            start = ToDoTheme.tDDimensions.paddingS,
            bottom = ToDoTheme.tDDimensions.paddingXs
        ),
        text = title,
        style = ToDoTheme.tDTypography.inputLabel
    )
}

enum class InputFieldType {
    Default,
    Disabled
}