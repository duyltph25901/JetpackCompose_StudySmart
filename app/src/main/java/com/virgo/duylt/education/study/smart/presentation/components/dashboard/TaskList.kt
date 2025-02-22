package com.virgo.duylt.education.study.smart.presentation.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.R
import com.virgo.duylt.education.study.smart.domain.model.Task
import com.virgo.duylt.education.study.smart.utils.Converter.convertLongToDate
import com.virgo.duylt.education.study.smart.utils.Priority

fun LazyListScope.taskList(
    sectionTitle: String = "",
    emptyListText: String = "",
    listTask: List<Task> = mutableListOf(),
    onTaskCardClick: (Long) -> Unit,
    onCheckboxClick: (Task) -> Unit
) {
    item {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(
                all = 12.dp
            )
        )
    }

    if (listTask.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.img_tasks),
                    contentDescription = emptyListText
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = emptyListText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    items(
        items = listTask,
        key = { task ->
            task.taskId
        }
    ) { task ->
        TaskCard(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 4.dp
            ),
            task = task,
            onCheckboxClick = {
                onCheckboxClick.invoke(task)
            },
            onClick = {
                onTaskCardClick.invoke(task.taskId)
            }
        )
    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task = Task(),
    onCheckboxClick: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = {
            onClick.invoke()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskCheckBox(
                isCompleted = task.isComplete,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckboxClick = {
                    onCheckboxClick.invoke()
                }
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isComplete) TextDecoration.LineThrough
                    else TextDecoration.None
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = convertLongToDate(task.dueDate),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}