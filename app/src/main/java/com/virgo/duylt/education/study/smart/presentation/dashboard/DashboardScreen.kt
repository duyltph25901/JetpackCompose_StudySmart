package com.virgo.duylt.education.study.smart.presentation.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.presentation.components.CountCard

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
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            all = 12.dp
                        ),
                    subjectCount = 5 ,
                    studiesHours = 10,
                    goalStudiesHours = 15,
                )
            }
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