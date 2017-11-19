package com.isolity.toastalarm.alarm

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.isolity.toastalarm.WeeklyAlarmDataManager
import com.isolity.toastalarm.WeeklyAlarmDataManager.weeklyAlarms
import com.isolity.toastalarm.model.WeeklyAlarmUtil
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by shoarai on 2017/04/29.
 */

object WeeklyAlarmManager {
    private val TAG = WeeklyAlarmManager::class.java.simpleName

    /**
     * Start a next alarm with power on.
     * If there is no powered on, do not do anything.
     * @param context Context
     */
    fun startNextAlarm(context: Context) {
        Log.v(TAG, "start startNextAlarmWithPowerOn")

        WeeklyAlarmDataManager.init(context)

        if (WeeklyAlarmUtil.hasPowerOn(weeklyAlarms)) {
            val weeklyAlarms = WeeklyAlarmDataManager.getAll()
            val calendar = WeeklyAlarmUtil.getNextAlarmAsCalendar(weeklyAlarms)
            OnceAlarmManager.startAlarm(context, calendar)
        } else {
            OnceAlarmManager.stopAlarm()
        }

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