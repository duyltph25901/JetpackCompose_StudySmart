package com.virgo.duylt.education.study.smart.domain.model

import androidx.compose.ui.graphics.Color
import com.virgo.duylt.education.study.smart.presentation.theme.gradient1
import com.virgo.duylt.education.study.smart.presentation.theme.gradient2
import com.virgo.duylt.education.study.smart.presentation.theme.gradient3
import com.virgo.duylt.education.study.smart.presentation.theme.gradient4
import com.virgo.duylt.education.study.smart.presentation.theme.gradient5

data class Subject(
    val subjectId: Long = 0L,
    val name: String = "",
    val goalHours: Float = 0f,
    val colors: List<Color> = mutableListOf(),
) {

    companion object {
        val subjectCardColor =
            listOf(
                gradient1, gradient2, gradient3, gradient4, gradient5
            )
    }

}