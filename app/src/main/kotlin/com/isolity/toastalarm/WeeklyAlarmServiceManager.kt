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

    fun startAlarm(context: Context) {
        Log.v(TAG, "startAlarm")

        if (WeeklyAlarmManager.hasPowerOn()) {
            var calendar = WeeklyAlarmManager.getNextAlarmCalendar()
            AlarmService.startAlarm(context, calendar)

            val now = Calendar.getInstance()
            val diffMilli = calendar.timeInMillis - now.timeInMillis
            val diff = TimeUnit.MILLISECONDS.toMinutes(diffMilli)
            val hours = diff / 60
            val minutes = diff % 60
            Toast.makeText(context, "Start alarm: $hours:$minutes", 0).show()
        } else {
            AlarmService.stopAlarm()
            Toast.makeText(context, "Stop all alarm", 0).show()
        }
    }
}