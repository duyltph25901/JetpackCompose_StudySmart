package com.virgo.duylt.education.study.smart.presentation.dashboard

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.R
import com.virgo.duylt.education.study.smart.domain.model.Subject
import com.virgo.duylt.education.study.smart.presentation.components.CountCard
import com.virgo.duylt.education.study.smart.presentation.components.SubjectCard
import com.virgo.duylt.education.study.smart.presentation.components.sessionList
import com.virgo.duylt.education.study.smart.presentation.components.taskList
import com.virgo.duylt.education.study.smart.utils.SessionUtils.getDefaultSessions
import com.virgo.duylt.education.study.smart.utils.SubjectUtils.getSubjectsDefault
import com.virgo.duylt.education.study.smart.utils.TaskUtils.getTasksDefault

@Composable
fun DashboardScreen() {
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
                    subjectList = getSubjectsDefault()
                )
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth()
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
    emptyListText: String = "You don't have any subject.\nClick the + button to add new subject."
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
                modifier = Modifier.size(
                    size = 120.dp
                ).align(
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