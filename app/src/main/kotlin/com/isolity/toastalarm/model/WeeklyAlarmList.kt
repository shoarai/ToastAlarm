package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarmList {
    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()

    constructor(weeklyAlarms: Array<WeeklyAlarm>) {
        this.weeklyAlarms = weeklyAlarms.toMutableList()
    }

    fun hasPowerOn(): Boolean {
        weeklyAlarms.any {
            it.weeks.isNotEmpty() && it.timeAlarms.any { it.isPowerOn }
        }
        return false
    }

    fun findWeeklyAlarm(weeklyAlarmId: Int): WeeklyAlarm {
        var weeklyAlarm = weeklyAlarms.firstOrNull { it.id === weeklyAlarmId }
        if (weeklyAlarm === null) {
            throw IllegalArgumentException("weekly alarm is not found")
        }
        return weeklyAlarm
    }

    fun findWeeklyAlarmByTimeAlarmId(timeAlarmId: Int): WeeklyAlarm {
        var weeklyAlarm = weeklyAlarms.firstOrNull { it.timeAlarms.any { it.id == timeAlarmId } }
        if (weeklyAlarm === null) {
            throw IllegalArgumentException("weekly alarm is not found")
        }
        return weeklyAlarm
    }

    fun findTimeAlarm(timeAlarmId: Int): TimeAlarm {
        var timeAlarm = weeklyAlarms.flatMap { it.timeAlarms }
                .firstOrNull { it.id == timeAlarmId }
        if (timeAlarm === null) {
            throw IllegalArgumentException("time alarm is not found")
        }
        return timeAlarm
    }
}
