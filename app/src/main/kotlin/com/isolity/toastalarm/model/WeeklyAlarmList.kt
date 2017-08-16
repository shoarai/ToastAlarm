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
       return weeklyAlarms.any {
            it.weeks.isNotEmpty() && it.dailyAlarms.any { it.isPowerOn }
        }
    }

    fun findWeeklyAlarm(weeklyAlarmId: Int): WeeklyAlarm {
        var weeklyAlarm = weeklyAlarms.firstOrNull { it.id === weeklyAlarmId }
        if (weeklyAlarm === null) {
            throw IllegalArgumentException("weekly alarm is not found")
        }
        return weeklyAlarm
    }

    fun findWeeklyAlarmByTimeAlarmId(timeAlarmId: Int): WeeklyAlarm {
        var weeklyAlarm = weeklyAlarms.firstOrNull { it.dailyAlarms.any { it.id == timeAlarmId } }
        if (weeklyAlarm === null) {
            throw IllegalArgumentException("weekly alarm is not found")
        }
        return weeklyAlarm
    }

    fun findTimeAlarm(timeAlarmId: Int): DailyAlarm {
        var timeAlarm = weeklyAlarms.flatMap { it.dailyAlarms }
                .firstOrNull { it.id == timeAlarmId }
        if (timeAlarm === null) {
            throw IllegalArgumentException("time alarm is not found")
        }
        return timeAlarm
    }
}
