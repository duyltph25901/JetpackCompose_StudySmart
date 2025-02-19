package com.virgo.duylt.education.study.smart.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.R
import com.virgo.duylt.education.study.smart.domain.model.Session
import com.virgo.duylt.education.study.smart.utils.Converter.convertDurationToHours
import com.virgo.duylt.education.study.smart.utils.Converter.convertLongToDate

fun LazyListScope.sessionList(
    sectionTitle: String = "",
    emptyListStudySession: String = "",
    listStudySession: List<Session> = mutableListOf(),
    onSessionClick: (Session) -> Unit,
    onSessionDeleteClick: (Session) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(
                all = 12.dp
            ),
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall
        )
    }

    if (listStudySession.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(
                        size = 120.dp
                    ),
                    painter = painterResource(R.drawable.img_lamp),
                    contentDescription = ""
                )

                Spacer(
                    modifier = Modifier.height(
                        height = 12.dp
                    )
                )

                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = emptyListStudySession,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    items(listStudySession) { session ->
        SessionCard(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                ),
            session = session,
            onSessionClick = {
                onSessionClick.invoke(session)
            },
            onDeleteSessionClick = {
                onSessionDeleteClick.invoke(session)
            }
        )
    }
}

@Composable
fun SessionCard(
    modifier: Modifier = Modifier,
    session: Session = Session(),
    onSessionClick: () -> Unit,
    onDeleteSessionClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = {
            onSessionClick.invoke()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .padding(
                        end = 12.dp
                    )
            ) {
                Text(
                    text = session.relatedToSubject,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = convertLongToDate(session.date),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = convertDurationToHours(session.duration)
            )

            IconButton(
                onClick = {
                    onDeleteSessionClick.invoke()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}