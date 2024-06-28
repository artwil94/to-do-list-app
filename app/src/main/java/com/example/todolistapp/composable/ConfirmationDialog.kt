package com.example.todolistapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.todolistapp.ui.theme.ToDoTheme

@Preview
@Composable
fun ConfirmationDialogPreview(
    onConfirm: () -> Unit = {},
) {
    ConfirmationDialog(
        title = "Are you sure?",
        message = null,
        confirmText = "Remove",
        cancelText = "Cancel",
        onConfirm = { onConfirm.invoke() },
    )
}

@Composable
fun ConfirmationDialog(
    title: String,
    message: String? = null,
    confirmText: String,
    cancelText: String? = null,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = { if (cancelText == null) onConfirm.invoke() else onCancel.invoke() }
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = ToDoTheme.tDColors.backgroundScreen,
                    shape = ToDoTheme.tDShapes.confirmationDialog
                )
        ) {
            Column(
                modifier = Modifier.padding(
                    ToDoTheme.tDDimensions.paddingMedium,
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = ToDoTheme.tDTypography.confirmationDialogTitle
                )
                message?.let {
                    Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingS))
                    Text(
                        text = message,
                        style = ToDoTheme.tDTypography.confirmationDialogMessage
                    )
                }
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingMedium))
                ActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = confirmText,
                    inverted = true,
                    onClick = { onConfirm.invoke() })
                Spacer(modifier = Modifier.height(ToDoTheme.tDDimensions.paddingL))
                cancelText?.let {
                    ActionButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = cancelText,
                        actionButtonType = ActionButtonType.Cancel,
                        onClick = { onCancel.invoke() })
                }
            }
        }
    }
}