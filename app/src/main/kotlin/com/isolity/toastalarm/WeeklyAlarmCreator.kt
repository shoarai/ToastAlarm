package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.util.*

/**
 * Created by shohei52a on 2017/08/13.
 */

object WeeklyAlarmCreator {
    /**
     * Create weekly alarm for current time.
     */
    fun createWeeklyAlarm(): WeeklyAlarm {
        val weeklyAlarms = WeeklyAlarmManager.weeklyAlarms.toTypedArray()

        var c = Calendar.getInstance()
        var timeOfDay = TimeOfDay(
                c.get((Calendar.HOUR_OF_DAY)),
                c.get((Calendar.MINUTE)))
        var timeAlarm = TimeAlarm(createTimeAlarmId(weeklyAlarms), timeOfDay)
        var weeklyAlarm = WeeklyAlarm(createUniqueId(weeklyAlarms), timeAlarm)
        return weeklyAlarm
    }

    /**
     * Create unique ID for weekly alarm.
     */
    fun createUniqueId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        var i = 1
        while (true) {
            if (weeklyAlarms.any { it.id === i }) {
                i++
                continue
            }
            return i
        }
    }

    /**
     * Create unique ID for time alarm.
     */
    fun createTimeAlarmId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        var i = 1
        while (true) {
            if (weeklyAlarms.any { it.timeAlarms.any { it.id === i } }) {
                i++
                continue
            }
            return i
        }
    }
}