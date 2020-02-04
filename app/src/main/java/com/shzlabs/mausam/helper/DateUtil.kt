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

}