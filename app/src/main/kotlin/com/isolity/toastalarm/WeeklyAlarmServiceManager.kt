package com.isolity.toastalarm

import android.content.Context
import android.util.Log
import java.util.*

/**
 * Created by shoarai on 2017/04/29.
 */

object WeeklyAlarmServiceManager {

    private val TAG = WeeklyAlarmServiceManager::class.java.simpleName

    fun startAlarm(context: Context) {
        Log.v(TAG, "startAlarm")

        if (WeeklyAlarmManager.hasPowerOn()) {
            var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms
            val calendar = WeeklyAlarmFilter.getNextAlarmCalendar(weeklyAlarms.toTypedArray())
            AlarmService.startAlarm(context, calendar)

//            Toast.makeText(context, "Next alarm: " + toString(calendar), 0).show()

//            val now = Calendar.getInstance()
//            val diffMilli = calendar.timeInMillis - now.timeInMillis
//            val diff = TimeUnit.MILLISECONDS.toMinutes(diffMilli)
//            val hours = diff / 60
//            val minutes = diff % 60
//            Toast.makeText(context, "Start alarm: $hours:$minutes", 0).show()
        } else {
            AlarmService.stopAlarm()
//            Toast.makeText(context, "Stop all alarm", 0).show()
        }
    }

    fun toString(c: Calendar): String {
        return (c.get(Calendar.MONTH) + 1).toString() + '/' +
                (c.get(Calendar.DAY_OF_MONTH)).toString() + ' ' +
                (c.get(Calendar.HOUR_OF_DAY)).toString() + ':' +
                (c.get(Calendar.MINUTE)).toString()
    }
}