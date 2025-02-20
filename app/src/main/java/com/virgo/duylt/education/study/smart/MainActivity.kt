package com.virgo.duylt.education.study.smart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.virgo.duylt.education.study.smart.presentation.ui.dashboard.DashboardScreen
import com.virgo.duylt.education.study.smart.presentation.theme.StudySmartTheme
import com.virgo.duylt.education.study.smart.presentation.ui.subject.SubjectScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {
                SubjectScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainPreview() {
    StudySmartTheme {
        DashboardScreen()
    }
}