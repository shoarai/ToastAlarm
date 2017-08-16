package com.isolity.toastalarm

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by shoarai on 2017/04/29.
 */

object WeeklyAlarmServiceManager {

    private val TAG = WeeklyAlarmServiceManager::class.java.simpleName

    /**
     * Start a next alarm with power on.
     * If there is no powered on, do not do anything.
     * @param context context
     */
    fun startNextAlarmWithPowerOn(context: Context) {
        Log.v(TAG, "start startNextAlarmWithPowerOn")

        WeeklyAlarmManager.init(context)

        if (WeeklyAlarmManager.hasPowerOn()) {
            var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms.toTypedArray()
            val calendar = WeeklyAlarmFilter.getNextAlarmAsCalendar(weeklyAlarms)
            AlarmService.startAlarm(context, calendar)

//            showNextTime(context, calendar)
        } else {
            AlarmService.stopAlarm()
        }

        Log.v(TAG, "end startNextAlarmWithPowerOn")
    }

    private fun showNextTime(context: Context, calendar: Calendar){
//            Toast.makeText(context, "Next alarm: " + toString(calendar), 0).show()

        val now = Calendar.getInstance()
        val diffMilli = calendar.timeInMillis - now.timeInMillis
        val diff = TimeUnit.MILLISECONDS.toMinutes(diffMilli)
        val hours = diff / 60
        val minutes = diff % 60
        Toast.makeText(context, "Next alarm is after: $hours:$minutes", 0).show()
    }

    fun toString(c: Calendar): String {
        return (c.get(Calendar.MONTH) + 1).toString() + '/' +
                (c.get(Calendar.DAY_OF_MONTH)).toString() + ' ' +
                (c.get(Calendar.HOUR_OF_DAY)).toString() + ':' +
                (c.get(Calendar.MINUTE)).toString()
    }
}