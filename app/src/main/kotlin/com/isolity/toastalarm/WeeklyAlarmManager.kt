package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmManager {
    var weeklyAlarms: Array<WeeklyAlarm> = emptyArray()
        private set

    init {
        var weeklyAlarm = WeeklyAlarm()
        var alarm = TimeAlarm(TimeOfDay(23, 23))
        alarm.powerOn()
        weeklyAlarm.timeAlarms = arrayOf(
                ToastAlarmSettingManager.getNow(),
                alarm,
                ToastAlarmSettingManager.getNow()
        )
        weeklyAlarm.weeks = setOf(Calendar.TUESDAY, Calendar.FRIDAY)

        weeklyAlarms = arrayOf(
                weeklyAlarm,
                weeklyAlarm,
                weeklyAlarm)
    }

    fun hasPowerOn(): Boolean {
        weeklyAlarms.forEach { weeklyAlarm ->
            weeklyAlarm.timeAlarms.forEach {
                if (it.isPowerOn)
                    return true
            }
        }
        return false
    }

    fun getNextAlarmCalendar(): Calendar {
        var calendar: Calendar? = null

        weeklyAlarms.forEach {
            var cal = getNextAlarmCalendar(it)
            if (cal === null) {
                return@forEach
            }
            if (calendar === null || cal.before(calendar)) {
                calendar = cal
            }
        }

        if (calendar === null) {
            throw IllegalStateException("Not found alarm on power")
        }
        return calendar as Calendar
    }

    private fun getNextAlarmCalendar(weeklyAlarm: WeeklyAlarm): Calendar? {
        var calendar: Calendar? = null

        weeklyAlarm.weeks.forEach { week ->
            weeklyAlarm.timeAlarms
                    .filter { it.isPowerOn }
                    .forEach {
                        var cal = toCalendarAfterNow(it.timeOfDay, week)
                        if (calendar === null || cal.before(calendar)) {
                            calendar = cal
                        }
                    }
        }

        return calendar
    }

    private fun toCalendarAfterNow(timeOfDay: TimeOfDay, week: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, week)
        calendar.set(Calendar.HOUR_OF_DAY, timeOfDay.hour)
        calendar.set(Calendar.MINUTE, timeOfDay.minute)
        calendar.set(Calendar.SECOND, 0)

        val now = Calendar.getInstance()
        if (calendar.before(now)) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        return calendar
    }
}
