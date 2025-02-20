package com.virgo.duylt.education.study.smart.presentation.ui.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.domain.model.Session
import com.virgo.duylt.education.study.smart.domain.model.Subject
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.CountCard
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.sessionList
import com.virgo.duylt.education.study.smart.presentation.components.dashboard.taskList
import com.virgo.duylt.education.study.smart.presentation.components.dialog.AddSubjectDialog
import com.virgo.duylt.education.study.smart.presentation.components.dialog.DeleteDialog
import com.virgo.duylt.education.study.smart.utils.SessionUtils.getDefaultSessions
import com.virgo.duylt.education.study.smart.utils.TaskUtils.getTasksDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen() {

    var isDeleteSessionDialog by remember { mutableStateOf(false) }
    var sessionDeleteCurrent by remember { mutableStateOf<Session?>(null) }
    var isDeleteSubjectDialog by remember { mutableStateOf(false) }
    var isAddSubjectDialog by remember { mutableStateOf(false) }
    var isEditSubjectDialog by remember { mutableStateOf(false) }
    val taskList by rememberSaveable { mutableStateOf(getTasksDefault()) }
    val sessionList by rememberSaveable { mutableStateOf(getDefaultSessions()) }
    var selectedColor by rememberSaveable { mutableStateOf(Subject.subjectCardColor.random()) }
    var subjectName by rememberSaveable { mutableStateOf("") }
    var goalHours by rememberSaveable { mutableStateOf("") }

    val listState = rememberLazyListState()
    val isFABExpanded by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    fun hideDeleteSessionDialog() {
        isDeleteSessionDialog = false
    }

    fun showDeleteSessionDialog() {
        isDeleteSessionDialog = true
    }

    fun hideDeleteSubjectDialog() {
        isDeleteSubjectDialog = false
    }

    fun showDeleteSubjectDialog() {
        isDeleteSubjectDialog = true
    }

    fun hideDialogEditSubject() {
        isEditSubjectDialog = false
    }

    fun showDialogEditSubject() {
        isEditSubjectDialog = true
    }

    fun showDialogAddSubject() {
        isAddSubjectDialog = true
    }

    fun hideDialogAddSubject() {
        isAddSubjectDialog = false
    }

    // Dialog Delete Item Session
    DeleteDialog(
        isOpen = isDeleteSessionDialog,
        title = "Delete Session?",
        bodyText = "Are you sure, you want to delete ${sessionDeleteCurrent?.relatedToSubject ?: "this" } session? Your studies hours will be reduced by this session time." +
                " This action can not be undo.",
        onDismissRequest = {
            hideDeleteSessionDialog()
        },
        onConfirmButtonClick = {
            hideDeleteSessionDialog()
        }
    )

    // Dialog Delete Subject
    DeleteDialog(
        isOpen = isDeleteSessionDialog,
        title = "Delete subject?",
        bodyText = "Are you sure, you want to delete this subject? All related" +
                " tasks and study sessions will be pernametly removed. This action can not be undone.",
        onDismissRequest = {
            hideDeleteSubjectDialog()
        },
        onConfirmButtonClick = {
            hideDeleteSubjectDialog()
        }
    )

    // Dialog Edit Subject
    AddSubjectDialog(
        isOpen = isEditSubjectDialog,
        selectedColors = selectedColor,
        subjectName = subjectName,
        goalHours = goalHours,
        maxLengthSubject = 31,
        maxLengthGoalHours = 4,

        onDismissRequest = {
            hideDialogEditSubject()
        },
        onConfirmClick = {
            hideDialogEditSubject()
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

    // Dialog Edit Subject
    AddSubjectDialog(
        isOpen = isAddSubjectDialog,
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

    Scaffold(
        modifier = Modifier.nestedScroll(
            connection = scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                scrollBehavior = scrollBehavior,
                onBackButtonClick = {},
                onEditButtonClick = {
                    showDialogEditSubject()
                },
                onDeleteButtonClick = {
                    showDeleteSubjectDialog()
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    showDialogAddSubject()
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add task"
                    )
                },
                text = {
                    Text(
                        text = "Add task",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                expanded = isFABExpanded
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = innerPadding
                ),
            state = listState
        ) {
            item {
                SubjectOverviewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            all = 12.dp
                        ),
                    studiedHours = 2,
                    goalHours = 0.09,
                    progress = .04f
                )
            }

            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any upcoming task.\nClick the + button in subject screen to add new task.",
                listTask = taskList,
                onCheckboxClick = { task ->

                },
                onTaskCardClick = { id ->

                }
            )

            sessionList(
                sectionTitle = "RECENT STUDY SESSCIONS",
                emptyListStudySession = "You don't have any recent study session.\nStart a study session to begin recording your progress.",
                listStudySession = sessionList,
                onSessionClick = { session ->

                },
                onSessionDeleteClick = { session ->
                    sessionDeleteCurrent = session
                    showDeleteSessionDialog()
                }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
) {
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackButtonClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    onDeleteButtonClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete subject",
                )
            }

            IconButton(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    onEditButtonClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit subject",
                )
            }
        }
    )
}

@Composable
private fun SubjectOverviewSection(
    modifier: Modifier = Modifier,
    studiedHours: Number = 0,
    goalHours: Number = 0,
    progress: Number = 0
) {

    val percentageProgress = remember(progress) {
        (progress.toFloat() * 100).toInt().coerceIn(0, 100)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CountCard(
            modifier = Modifier.weight(
                weight = 1f
            ),
            headingText = "Goal Study Hours",
            count = goalHours
        )

        Spacer(
            modifier = Modifier.width(
                width = 10.dp
            )
        )

        CountCard(
            modifier = Modifier.weight(
                weight = 1f
            ),
            headingText = "Studied Hours",
            count = studiedHours
        )

        Spacer(
            modifier = Modifier.width(
                width = 10.dp
            )
        )

        Box(
            modifier = Modifier.size(
                    size = 80.dp
                ),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )

            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress.toFloat(),
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )

            Text(
                text = "${percentageProgress}%",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}