package com.isolity.toastalarm.application

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.isolity.toastalarm.application.library.OnceAlarmManager
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import com.isolity.toastalarm.domain.service.NextAlarmFilter
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by shoarai on 2017/04/29.
 */

object WeeklyAlarmManager {
    private val TAG = WeeklyAlarmManager::class.java.simpleName

    /**
     * Start a next alarm with power on.
     * If there is no powered on, stop the previous alarm.
     * @param context Context
     * @param weeklyAlarms List of weekly alarm
     */
    fun startNextAlarm(context: Context, weeklyAlarms: List<WeeklyAlarm>) {
        Log.v(TAG, "start startNextAlarmWithPowerOn")

        NextAlarmFilter.getNextAlarmCalendar(weeklyAlarms)?.let {
            OnceAlarmManager.startAlarm(context, it)
        } ?: OnceAlarmManager.stopAlarm()

        Log.v(TAG, "end startNextAlarmWithPowerOn")
    }

    private fun showNextTime(context: Context, calendar: Calendar) {
//            Toast.makeText(context, "Next alarm: " + toString(calendar), 0).show()

        val now = Calendar.getInstance()
        val diffMilli = calendar.timeInMillis - now.timeInMillis
        val diff = TimeUnit.MILLISECONDS.toMinutes(diffMilli)
        val hours = diff / 60
        val minutes = diff % 60
        Toast.makeText(context, "Next alarm is after: $hours:$minutes", Toast.LENGTH_LONG).show()
    }

    private fun calendarString(c: Calendar): String {
        return (c.get(Calendar.MONTH) + 1).toString() + '/' +
                (c.get(Calendar.DAY_OF_MONTH)).toString() + ' ' +
                (c.get(Calendar.HOUR_OF_DAY)).toString() + ':' +
                (c.get(Calendar.MINUTE)).toString()
    }
}