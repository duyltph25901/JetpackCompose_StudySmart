package com.virgo.duylt.education.study.smart.presentation.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.domain.model.Subject

@Composable
fun AddSubjectDialog(
    isOpen: Boolean = false,
    title: String = "Add/Update Subject",
    selectedColors: List<Color> = listOf(),
    subjectName: String = "",
    goalHours: String = "",
    maxLengthSubject: Int = 30,
    maxLengthGoalHours: Int = 3,

    onColorChange: (List<Color>) -> Unit,
    onSubjectNameChange: (String) -> Unit,
    onGoalHoursChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
) {

    var subjectNameError by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var goalHoursError by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    subjectNameError = when {
        subjectName.isBlank() -> "Please enter subject name."
        subjectName.length < 2 -> "Subject name is too short."
        subjectName.length == maxLengthSubject -> "Subject is too long."
        else -> null
    }

    goalHoursError = when {
        goalHours.isBlank() -> "Please enter goal study hours."
        goalHours.toFloatOrNull() == null -> "Invalid number"
        goalHours.toFloat() < 1f -> "Please set at least 1 hour"
        goalHours.toFloat() > 10f -> "Please set a maximum of 10 hours"
        else -> null
    }

    if(isOpen) {
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
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                bottom = 16.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Subject.subjectCardColor.forEach { colors ->
                            Box(
                                modifier = Modifier.size(
                                    size = 24.dp
                                ).clip(
                                    shape = CircleShape
                                ).border(
                                    width = 1.dp,
                                    color = if (colors == selectedColors) Color.Black
                                    else Color.Transparent,
                                    shape = CircleShape
                                ).background(
                                    brush = Brush.verticalGradient(colors = colors)
                                ).clickable {
                                    onColorChange.invoke(colors)
                                }
                            )
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                bottom = 8.dp
                            ),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Gray
                        ),
                        placeholder = {
                            Text(
                                text = "Subject name",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                        label = {
                            Text(
                                text = "Subject name",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        value = subjectName,
                        singleLine = true,
                        maxLines = 1,
                        isError = subjectNameError != null && subjectName.isBlank(),
                        supportingText = {
                            Text(
                                text = subjectNameError.orEmpty()
                            )
                        },
                        onValueChange = { newValue ->
                            if (newValue.length < maxLengthSubject) {
                                onSubjectNameChange.invoke(newValue)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                bottom = 8.dp
                            ),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Gray
                        ),
                        placeholder = {
                            Text(
                                text = "Goal Study Hours",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                        label = {
                            Text(
                                text = "Goal Study Hours",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        value = goalHours,
                        singleLine = true,
                        maxLines = 1,
                        isError = goalHoursError != null && goalHours.isBlank(),
                        supportingText = {
                            Text(
                                text = goalHoursError.orEmpty()
                            )
                        },
                        onValueChange = { newValue ->
                            if (newValue.length < maxLengthGoalHours) {
                                onGoalHoursChange.invoke(newValue)
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                            }
                        ),
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmClick.invoke()
                    },
                    enabled = subjectNameError == null && goalHoursError == null
                ) {
                    Text(
                        text = "Save"
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