package com.virgo.duylt.education.study.smart.presentation.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.TaskCheckBox
import com.virgo.duylt.education.study.smart.presentation.components.dialog.DeleteDialog
import com.virgo.duylt.education.study.smart.presentation.theme.Red
import com.virgo.duylt.education.study.smart.presentation.theme.StudySmartTheme
import com.virgo.duylt.education.study.smart.utils.Priority

@Composable
fun TaskScreen() {
    var isOpenDeleteDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }
    taskTitleError = when {
        title.isBlank() -> "Please enter the task title"
        title.length < 4 -> "Task title is too short"
        else -> null
    }

    DeleteDialog(
        isOpen = isOpenDeleteDialog,
        title = "Delete Task?",
        bodyText = "Are you sure, you want to delete this task? " +
                "This action can not be undone.",
        onDismissRequest = {
            isOpenDeleteDialog = false
        },
        onConfirmButtonClick = {
            isOpenDeleteDialog = false
        }
    )

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isTaskExist = true,
                isCompleted = false,
                checkBoxBorderColor = Red,
                onBackButtonClick = {},
                onCheckBoxButtonClick = {},
                onDeleteButtonClick = {
                    isOpenDeleteDialog = true
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(
                    paddingValues = innerPadding
                )
                .padding(
                    horizontal = 12.dp
                ),
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { newValue ->
                    title = newValue
                },
                singleLine = true,
                label = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                placeholder = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                isError = taskTitleError != null && title.isNotBlank(),
                supportingText = {
                    Text(
                        text = taskTitleError ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                textStyle = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(
                    height = 12.dp
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { newValue ->
                    description = newValue
                },
                label = {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                placeholder = {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                textStyle = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(
                    height = 12.dp
                )
            )

            Text(
                text = "Due date",
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "12 Oct 2025",
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Range"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(
                    height = 12.dp
                )
            )

            Text(
                text = "Priority",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(
                    height = 12.dp
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Priority.entries.forEach { priority ->
                    PriorityButton(
                        modifier = Modifier.weight(weight = 1f),
                        label = priority.title,
                        backgroundColor = priority.color,
                        borderColor = if (
                            priority == Priority.MEDIUM
                        ) Color.White else Color.Transparent,
                        labelColor = if (
                            priority == Priority.MEDIUM
                        ) Color.White else Color.White.copy(
                            alpha = .7f
                        ),
                        onClickButton = {}
                    )
                }
            }

            Spacer(modifier = Modifier.height(height = 12.dp))

            Text(
                text = "Relate to subject",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(height = 12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "English",
                    style = MaterialTheme.typography.bodyLarge
                )
                
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.height(height = 12.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = taskTitleError == null && title.isNotBlank(),
            ) { 
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(
    isTaskExist: Boolean = false,
    isCompleted: Boolean = false,
    checkBoxBorderColor: Color = Color.Red,
    onBackButtonClick: () -> Unit,
    onCheckBoxButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackButtonClick.invoke()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back navigation"
                )
            }
        },
        title = {
            Text(
                text = "Task",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            if (isTaskExist) {
                TaskCheckBox(
                    isCompleted = isCompleted,
                    borderColor = checkBoxBorderColor,
                    onCheckboxClick = {
                        onCheckBoxButtonClick.invoke()
                    }
                )

                IconButton(
                    onClick = {
                        onDeleteButtonClick.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Button"
                    )
                }
            }
        }
    )
}

@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String = "",
    backgroundColor: Color = Color.Red,
    borderColor: Color = Color.Transparent,
    labelColor: Color = Color.White,
    onClickButton: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor)
            .padding(all = 4.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .clickable { onClickButton.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = labelColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskScreenPreview() {
    StudySmartTheme {
        TaskScreen()
    }
}