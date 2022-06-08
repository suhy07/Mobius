package edu.fzu.mobius.util

import java.util.*

class TimeUtils {
    companion object{
        @JvmStatic
        fun getTodayWeek():Int{
            val calendar: Calendar = Calendar.getInstance()
            return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1
        }
    }
}