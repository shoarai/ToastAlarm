package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarm(val id:Int) {
    var timeAlarms: Array<TimeAlarm> = emptyArray()
    var weeks: MutableSet<Int> = mutableSetOf()
        private set

    fun addWeek (week: Int) {
        weeks.add(week)
    }

    fun removeWeek (week:Int) {
        weeks.remove(week)
    }
}
