package edu.fzu.mobius.util

import android.util.Log
import java.util.*

class TimeUtils {
    companion object{
        @JvmStatic
        fun getTodayWeek():Int{
            val calendar: Calendar = Calendar.getInstance()
            Log.d("TAGTAG",((calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1).toString())
            return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1
        }
    }
}