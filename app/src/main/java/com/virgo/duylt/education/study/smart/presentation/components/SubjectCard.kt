package com.virgo.duylt.education.study.smart.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.virgo.duylt.education.study.smart.R

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subjectName: String = "Subject name",
    gradientColors: List<Color> = mutableListOf(),
    onclick: () -> Unit
) {
    Box(
        modifier = modifier.size(150.dp)
            .background(
                brush = Brush.verticalGradient(gradientColors),
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onclick.invoke()
            }
    ) {
        Column(
            modifier = Modifier.padding(
                all = 12.dp
            ),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(
                    size = 80.dp
                ),
                painter = painterResource(R.drawable.img_books),
                contentDescription = ""
            )

            Text(
                text = subjectName,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }
}