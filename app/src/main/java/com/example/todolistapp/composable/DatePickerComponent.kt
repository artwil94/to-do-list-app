package com.example.todolistapp.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun DatePickerComponent(datePickerState: DatePickerState, onSelectDateClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        DatePicker(
            state = datePickerState,
            modifier = Modifier.fillMaxWidth(),
            title = null,
            headline = null,
            showModeToggle = false
        )
        Button(onClick = { onSelectDateClick.invoke() }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "OK")
        }
    }
}