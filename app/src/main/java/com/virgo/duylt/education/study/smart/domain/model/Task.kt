package com.virgo.duylt.education.study.smart.domain.model

data class Task(
    var taskId: Long = 0L,
    var taskSubjectId: Long = 0L,
    var title: String = "",
    var description: String = "",
    var dueDate: Long = 0L,
    var priority: Int = 0,
    var relatedToSubject: String = "",
    var isComplete: Boolean = false
) {
}