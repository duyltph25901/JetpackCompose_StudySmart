package com.virgo.duylt.education.study.smart.presentation.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    isOpen: Boolean = false,
    title: String = "",
    bodyText: String = "",
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {

    if (isOpen) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            title = {
                Text(
                    text = title
                )
            },
            text = {
                Text(
                    text = bodyText
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButtonClick.invoke()
                    }
                ) {
                    Text(
                        text = "Delete"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest.invoke()
                    }
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        )
    }

}