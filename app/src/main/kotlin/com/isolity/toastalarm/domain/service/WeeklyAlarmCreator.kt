package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.entity.DailyAlarm
import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import java.lang.IllegalStateException

/**
 * Created by shoarai on 2017/08/13.
 */

object WeeklyAlarmCreator {
    /**
     * Create a weekly alarm at current time.
     * @param weeklyAlarms weekly alarms
     * @param timeOfDay time
     * @return new weekly alarm
     */
    fun create(weeklyAlarms: Array<WeeklyAlarm>, timeOfDay: TimeOfDay): WeeklyAlarm {
        var timeAlarm = createDailyAlarmSetting(weeklyAlarms, timeOfDay)
        return WeeklyAlarm(createUniqueId(weeklyAlarms), timeAlarm)
    }

    private fun createDailyAlarmSetting(weeklyAlarms: Array<WeeklyAlarm>, timeOfDay: TimeOfDay): DailyAlarm {
        return DailyAlarm(createTimeAlarmId(weeklyAlarms), timeOfDay).apply {
            powerOn()
        }
    }

    /**
     * Create an unique ID for new weekly alarm.
     */
    private fun createUniqueId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        for (i in 1..Int.MAX_VALUE) {
            if (!weeklyAlarms.any { it.id == i }) {
                return i
            }
        }
        throw IllegalStateException("Overflow of time alarm ID")
    }

    /**
     * Create an unique ID for new time alarm.
     */
    private fun createTimeAlarmId(weeklyAlarms: Array<WeeklyAlarm>): Int {
        for (i in 1..Int.MAX_VALUE) {
            if (!weeklyAlarms.any { it.dailyAlarms.any { it.id == i } }) {
                return i
            }
        }
        throw IllegalStateException("Overflow of weekly alarm ID")
    }
}