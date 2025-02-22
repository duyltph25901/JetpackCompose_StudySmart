package com.virgo.duylt.education.study.smart.utils

import com.virgo.duylt.education.study.smart.domain.model.Task

object TaskUtils {

    fun getTasksDefault() = listOf(
        Task(taskId = 11L, title = "Completes assignment", description = "Finish the chapter exercises", dueDate = 1697949427000, priority = 2, relatedToSubject = "Math", isComplete = false),
        Task(taskId = 12L, title = "Studies programming", description = "Read and practice Kotlin basics", dueDate = 1698035827000, priority = 1, relatedToSubject = "Programming", isComplete = true),
        Task(taskId = 23L, title = "Writes report", description = "Write a detailed report on the research", dueDate = 1698122227000, priority = 0, relatedToSubject = "Literature", isComplete = false),
        Task(taskId = 34L, title = "Reads book", description = "Complete reading the chapter on AI", dueDate = 1698208627000, priority = 2, relatedToSubject = "Science", isComplete = true),
        Task(taskId = 45L, title = "Studies programming", description = "Build a small Android app", dueDate = 1698295027000, priority = 1, relatedToSubject = "Programming", isComplete = false),
        Task(taskId = 56L, title = "Prepares notes", description = "Review all topics before the exam", dueDate = 1698381427000, priority = 0, relatedToSubject = "History", isComplete = false),
        Task(taskId = 67L, title = "Completes assignment", description = "Submit the final project proposal", dueDate = 1698467827000, priority = 2, relatedToSubject = "Math", isComplete = true),
        Task(taskId = 78L, title = "Writes report", description = "Write an analysis on the latest findings", dueDate = 1698554227000, priority = 1, relatedToSubject = "Science", isComplete = false)
    )


}