package com.hokagelab.doni.kadefootball.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    fun convertDate(dateTime: String): String? {
        val sdf = SimpleDateFormat("dd/MM/yy HH:mm:ssZ", Locale.getDefault())
        var date: Date? = null
        try {
            date = sdf.parse(dateTime)
        } catch (e: ParseException) {
        }
        return SimpleDateFormat("EEE, dd MMM yyyy , HH:mm").format(date).toString()
    }

}