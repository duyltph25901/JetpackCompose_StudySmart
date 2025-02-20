package com.virgo.duylt.education.study.smart.presentation.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.R
import com.virgo.duylt.education.study.smart.domain.model.Session
import com.virgo.duylt.education.study.smart.domain.model.Subject
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.CountCard
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.SubjectCard
import com.virgo.duylt.education.study.smart.presentation.components.dialog.AddSubjectDialog
import com.virgo.duylt.education.study.smart.presentation.components.dialog.DeleteDialog
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.sessionList
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.taskList
import com.virgo.duylt.education.study.smart.utils.SessionUtils.getDefaultSessions
import com.virgo.duylt.education.study.smart.utils.SubjectUtils.getSubjectsDefault
import com.virgo.duylt.education.study.smart.utils.TaskUtils.getTasksDefault

@Composable
fun DashboardScreen() {
    var isAddSubjectDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    var isDeleteDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    var subjectName by rememberSaveable {
        mutableStateOf("")
    }

    var goalHours by rememberSaveable {
        mutableStateOf("")
    }

    var selectedColor by rememberSaveable {
        mutableStateOf(
            Subject.subjectCardColor.random()
        )
    }

    var sessionDeleteCurrent by rememberSaveable {
        mutableStateOf<Session?>(null)
    }

    fun hideDialogAddSubject() {
        isAddSubjectDialogOpen = false
    }

    fun showDialogAddSubject() {
        isAddSubjectDialogOpen = true
    }

    fun hideDeleteDialog() {
        isDeleteDialogOpen = false
    }

    fun showDeleteDialog() {
        isDeleteDialogOpen = true
    }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        selectedColors = selectedColor,
        subjectName = subjectName,
        goalHours = goalHours,
        maxLengthSubject = 31,
        maxLengthGoalHours = 4,

        onDismissRequest = {
            hideDialogAddSubject()
        },
        onConfirmClick = {
            hideDialogAddSubject()
        },
        onColorChange = { colors ->
            selectedColor = colors
        },
        onSubjectNameChange = { newValue ->
            subjectName = newValue
        },
        onGoalHoursChange =  { newValue ->
            goalHours = newValue
        }
    )

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Session?",
        bodyText = "Are you sure, you want to delete ${sessionDeleteCurrent?.relatedToSubject ?: "this" } session? Your studies hours will be reduced by this session time." +
                " This action can not be undo.",
        onDismissRequest = {
            hideDeleteDialog()
        },
        onConfirmButtonClick = {
            hideDeleteDialog()
        }
    )

    Scaffold(
        topBar = {
            DashboardScreenTopBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            all = 12.dp
                        ),
                    subjectCount = 5,
                    studiesHours = 10,
                    goalStudiesHours = 15,
                )
            }

            item {
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = getSubjectsDefault(),
                    onAddIconClick = {
                        showDialogAddSubject()
                    }
                )
            }

            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 48.dp,
                            vertical = 20.dp
                        ),
                    onClick = {},
                ) {
                    Text(
                        text = "Start Study Session"
                    )
                }
            }

            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming task.\nClick the + button in subject screen to add new task.",
                listTask = getTasksDefault(),
                onCheckboxClick = { task ->

                },
                onTaskCardClick = { id ->

                }
            )

            sessionList(
                sectionTitle = "RECENT STUDY SESSCIONS",
                emptyListStudySession = "You don't have any recent study session.\nStart a study session to begin recording your progress.",
                listStudySession = getDefaultSessions(),
                onSessionClick = { session ->

                },
                onSessionDeleteClick = { session ->
                    sessionDeleteCurrent = session
                    showDeleteDialog()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Study Smart",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier = Modifier,
    subjectCount: Int,
    studiesHours: Int,
    goalStudiesHours: Int
) {
    Row(
        modifier = modifier
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = subjectCount
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studies Hours",
            count = studiesHours
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Studies Hours",
            count = goalStudiesHours
        )
    }
}

@Composable
private fun SubjectCardSection(
    modifier: Modifier = Modifier,
    subjectList: List<Subject> = mutableListOf(),
    emptyListText: String = "You don't have any subject.\nClick the + button to add new subject.",
    onAddIconClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = 12.dp
                )
            )

            IconButton(
                onClick = {
                    onAddIconClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }

        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(
                        size = 120.dp
                    )
                    .align(
                        alignment = Alignment.CenterHorizontally
                    ),
                painter = painterResource(R.drawable.img_books),
                contentDescription = ""
            )

            Text(
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp
            )
        ) {
            items(subjectList) { subject ->
                SubjectCard(
                    subjectName = subject.name,
                    gradientColors = subject.colors,
                    onclick = {

                    }
                )
            }
        }
    }
}