package com.shzlabs.mausam.helper

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getTimeFromEpoc(dt: Int): String {
        val dateFormat = SimpleDateFormat("h a")

        val instance = Calendar.getInstance()
        instance.timeInMillis = dt.toLong() * 1000

        return dateFormat.format(instance.time)
    }

    fun getDayString(day:Int): String{
        return when (day) {
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> ""
        }
    }

}