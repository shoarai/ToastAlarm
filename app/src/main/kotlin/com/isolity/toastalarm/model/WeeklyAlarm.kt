package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarm(val id: Int, timeAlarm: TimeAlarm) {
    var timeAlarms: MutableList<TimeAlarm> = mutableListOf(timeAlarm)
        private set

    var weeks: MutableSet<Int> = mutableSetOf()
        private set

    fun addWeek(week: Int) {
        weeks.add(week)
    }

    fun removeWeek(week: Int) {
        weeks.remove(week)
    }

    fun addTimeAlarm(timeAlarm: TimeAlarm) {
        timeAlarms.add(timeAlarm)
    }

    fun removeTimeAlarm(id: Int) {
        timeAlarms.removeAll { it.id === id }
    }
}
