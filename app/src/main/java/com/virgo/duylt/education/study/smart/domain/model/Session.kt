package com.virgo.duylt.education.study.smart.domain.model

data class Session(
    var sessionId: Long = 0L,
    var sessionSubjectId: Long = 0L,
    var date: Long = System.currentTimeMillis(),
    var duration: Long = 0L,
    var relatedToSubject: String = ""
) {
}