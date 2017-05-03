package com.isolity.toastalarm

import android.content.Context

/**
 * Created by shoarai on 2017/04/29.
 */

object WeeklyAlarmServiceManager {

    fun startAlarm(context: Context) {
        if (WeeklyAlarmManager.hasPowerOn()) {
            var calendar = WeeklyAlarmManager.getNextAlarmCalendar()
            AlarmService.startAlarm(context, calendar)
        } else {
            AlarmService.stopAlarm()
        }
    }

}