package com.shzlabs.mausam.helper

import com.shzlabs.mausam.data.model.ForecastItem
import java.util.*

object DataHelper {

    fun divideListByDay(items:List<ForecastItem>): MutableList<Any> {

        val origList = items.toMutableList()
        val modList: MutableList<Any> = mutableListOf()

        for (i in 0 .. origList.size-2) {

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = origList[i].dt.toLong() * 1000

            val calendarNext = Calendar.getInstance()
            calendarNext.timeInMillis = origList[i+1].dt.toLong() * 1000

            if (i == 0) {
                modList.add(getDayString(calendar.get(Calendar.DAY_OF_WEEK)))
            }

            if (calendar.get(Calendar.DAY_OF_YEAR) == calendarNext.get(Calendar.DAY_OF_YEAR)
                && calendar.get(Calendar.YEAR) == calendarNext.get(Calendar.YEAR)) {
                modList.add(origList[i])
                continue
            }

            modList.add(origList[i])
            modList.add(getDayString(calendarNext.get(Calendar.DAY_OF_WEEK)))
        }

        return modList

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

    fun capitalizeText(str: String): String {
        return str.substring(0,1).toUpperCase() + str.substring(1)
    }

}