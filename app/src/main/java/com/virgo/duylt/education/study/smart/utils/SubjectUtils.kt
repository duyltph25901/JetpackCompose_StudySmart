package com.virgo.duylt.education.study.smart.utils

import com.virgo.duylt.education.study.smart.domain.model.Subject

object SubjectUtils {

    fun getSubjectsDefault() = listOf(
        Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColor[0]),
        Subject(name = "Math", goalHours = 10f, colors = Subject.subjectCardColor[1]),
        Subject(name = "Physic", goalHours = 10f, colors = Subject.subjectCardColor[2]),
        Subject(name = "Geology", goalHours = 10f, colors = Subject.subjectCardColor[3]),
        Subject(name = "History", goalHours = 10f, colors = Subject.subjectCardColor[4]),
    )

}