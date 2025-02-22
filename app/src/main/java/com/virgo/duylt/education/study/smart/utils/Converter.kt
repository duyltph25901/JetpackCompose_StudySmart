package com.virgo.duylt.education.study.smart.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Converter {

    fun convertLongToDate(longDate: Long): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        return dateFormat.format(Date(longDate))
    }

    @SuppressLint("DefaultLocale")
    fun convertDurationToHours(duration: Long): String {
        val hours = duration / 3600000.0 // Chuyển từ milliseconds sang giờ (1h = 3600000ms)
        return String.format("%.1fh", hours)
    }

}