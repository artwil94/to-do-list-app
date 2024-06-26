package com.example.todolistapp.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
            .padding(ToDoTheme.tMDimensions.paddingS),
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
fun InputFieldWithLabel(text: String, label: String, onValueChange: (String) -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth()) {
        InputTopLabel(title = label)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 0.dp, color = Color.Black, shape = ToDoTheme.tDShapes.inputField)
                .height(50.dp),
            value = text,
            onValueChange = onValueChange,
            textStyle = ToDoTheme.tDTypography.inputField,
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            singleLine = true
        )
    }
}

@Composable
fun InputTopLabel(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(start = 8.dp),
        text = title,
        style = ToDoTheme.tDTypography.inputLabel
    )
}