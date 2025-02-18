package com.virgo.duylt.education.study.smart.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Converter {

    fun convertLongToDate(longDate: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(longDate))
    }
}