package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarm(val id: Int, timeAlarm: TimeAlarm) {
    var timeAlarms: MutableList<TimeAlarm> = mutableListOf(timeAlarm)

    var weeks: MutableSet<Int> = mutableSetOf()
        private set

    fun addWeek(vararg week: Int) {
        weeks.addAll(week.toList())
    }

    fun removeWeek(week: Int) {
        weeks.remove(week)
    }

    fun setTimeAlarm(id: Int, timeOfDay: TimeOfDay) {
        timeAlarms.first { it.id == id }.timeOfDay = timeOfDay
    }

    fun addTimeAlarm(timeAlarm: TimeAlarm) {
        timeAlarms.add(timeAlarm)
    }

    fun removeTimeAlarm(id: Int) {
        timeAlarms.removeAll { it.id === id }
    }
}
