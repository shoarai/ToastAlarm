package com.isolity.toastalarm.domain.entity

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarm(val id: Int, dailyAlarm: DailyAlarm) {
    var dailyAlarms: MutableList<DailyAlarm> = mutableListOf(dailyAlarm)

    var weeks: MutableSet<Int> = mutableSetOf()
        private set

    fun addWeek(vararg week: Int) {
        weeks.addAll(week.toList())
    }

    fun removeWeek(week: Int) {
        weeks.remove(week)
    }
}
