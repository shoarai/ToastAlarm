package com.isolity.toastalarm

import com.isolity.toastalarm.model.DailyAlarm
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
    fun createWeeklyAlarm(weeklyAlarms: Array<WeeklyAlarm>): WeeklyAlarm {
        var timeAlarm = createDailyAlarmSetting(weeklyAlarms)
        var weeklyAlarm = WeeklyAlarm(createUniqueId(weeklyAlarms), timeAlarm)
        weeklyAlarm.addWeek(
                Calendar.SUNDAY,
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
        )
        return weeklyAlarm
    }

    /**
     * Create default weekly alarms at first running.
     */
    fun createDefaultWeeklyAlarms(): Array<WeeklyAlarm> {
        return emptyArray()
    }

    private fun createDailyAlarmSetting(weeklyAlarms: Array<WeeklyAlarm>): DailyAlarm {
        var c = Calendar.getInstance()
        var timeOfDay = TimeOfDay(
                c.get((Calendar.HOUR_OF_DAY)),
                c.get((Calendar.MINUTE)))
        var timeAlarm = DailyAlarm(createTimeAlarmId(weeklyAlarms), timeOfDay)
        timeAlarm.powerOn()
        return timeAlarm
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
            if (weeklyAlarms.any { it.dailyAlarms.any { it.id === i } }) {
                i++
                continue
            }
            return i
        }
    }
}