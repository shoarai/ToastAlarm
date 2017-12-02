package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.entity.DailyAlarm
import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/08/13.
 */

object WeeklyAlarmCreator {
    /**
     * Create a weekly alarm at current time.
     * @param weeklyAlarms weekly alarms
     * @return new weekly alarm
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
     * Create an unique ID for new weekly alarm.
     */
    private fun createUniqueId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        var i = 1
        while (true) {
            if (weeklyAlarms.any { it.id == i }) {
                i++
                continue
            }
            return i
        }
    }

    /**
     * Create an unique ID for new time alarm.
     */
    private fun createTimeAlarmId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        var i = 1
        while (true) {
            if (weeklyAlarms.any { it.dailyAlarms.any { it.id == i } }) {
                i++
                continue
            }
            return i
        }
    }
}