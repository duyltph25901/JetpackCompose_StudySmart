package com.virgo.duylt.education.study.smart.presentation.components.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.presentation.theme.StudySmartTheme
import com.virgo.duylt.education.study.smart.presentation.ui.dashboard.DashboardScreen

@Composable
fun TaskCheckBox(
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false,
    borderColor: Color = Color.Red,
    onCheckboxClick: () -> Unit
) {

    Box(
        modifier = modifier.size(
            size = 24.dp
        ).clip(
            shape = CircleShape
        ).border(
            width = 2.dp,
            color = borderColor,
            shape = CircleShape
        ).clickable {
            onCheckboxClick.invoke()
        },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.padding(
                all = 4.dp
            ),
            visible = isCompleted
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = ""
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun TaskCheckBoxPreview() {
    StudySmartTheme {
        TaskCheckBox {

        }
    }
}