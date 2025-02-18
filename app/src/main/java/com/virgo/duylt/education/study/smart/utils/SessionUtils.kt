package com.virgo.duylt.education.study.smart.utils

import com.virgo.duylt.education.study.smart.domain.model.Session

object SessionUtils {

    fun getDefaultSessions() = listOf(
        Session(
            sessionId = 1L,
            sessionSubjectId = 101L,
            date = System.currentTimeMillis() - 86400000, // 1 ngày trước
            duration = 3600000, // 1 giờ
            relatedToSubject = "Math"
        ),
        Session(
            sessionId = 2L,
            sessionSubjectId = 102L,
            date = System.currentTimeMillis() - 172800000, // 2 ngày trước
            duration = 7200000, // 2 giờ
            relatedToSubject = "Science"
        ),
        Session(
            sessionId = 3L,
            sessionSubjectId = 103L,
            date = System.currentTimeMillis() - 259200000, // 3 ngày trước
            duration = 5400000, // 1.5 giờ
            relatedToSubject = "History"
        ),
        Session(
            sessionId = 4L,
            sessionSubjectId = 104L,
            date = System.currentTimeMillis() - 432000000, // 5 ngày trước
            duration = 10800000, // 3 giờ
            relatedToSubject = "Programming"
        ),
        Session(
            sessionId = 5L,
            sessionSubjectId = 105L,
            date = System.currentTimeMillis() - 604800000, // 7 ngày trước
            duration = 14400000, // 4 giờ
            relatedToSubject = "Literature"
        ),
        Session(
            sessionId = 6L,
            sessionSubjectId = 106L,
            date = System.currentTimeMillis() - 864000000, // 10 ngày trước
            duration = 18000000, // 5 giờ
            relatedToSubject = "Chemistry"
        ),
        Session(
            sessionId = 7L,
            sessionSubjectId = 107L,
            date = System.currentTimeMillis() - 1209600000, // 14 ngày trước
            duration = 7200000, // 2 giờ
            relatedToSubject = "Biology"
        ),
        Session(
            sessionId = 8L,
            sessionSubjectId = 108L,
            date = System.currentTimeMillis() - 1555200000, // 18 ngày trước
            duration = 5400000, // 1.5 giờ
            relatedToSubject = "Philosophy"
        )
    )


}