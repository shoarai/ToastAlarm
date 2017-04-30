package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException
import java.sql.Time
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmManager {
    var weeklyAlarms: Array<WeeklyAlarm> = emptyArray()
        private set

    private fun getAlarmsForDebug(): Array<WeeklyAlarm> {
        var weeklyAlarm = WeeklyAlarm(0)
        var alarm1 = TimeAlarm(0, TimeOfDay(8, 53))
        alarm1.powerOn()
        var alarm2 = TimeAlarm(1, TimeOfDay(12, 5))
        alarm2.powerOn()
        weeklyAlarm.timeAlarms = arrayOf(alarm1, alarm2)
        weeklyAlarm.addWeek(Calendar.MONDAY)
        weeklyAlarm.addWeek(Calendar.TUESDAY)
        weeklyAlarm.addWeek(Calendar.WEDNESDAY)
        weeklyAlarm.addWeek(Calendar.THURSDAY)
        weeklyAlarm.addWeek(Calendar.FRIDAY)

        var alarm3 = TimeAlarm(3, TimeOfDay(23, 53))
        var weeklyAlarm2 = WeeklyAlarm(1)
        weeklyAlarm2.timeAlarms = arrayOf(alarm3)

        return arrayOf(weeklyAlarm, weeklyAlarm2)
    }

    init {
//        weeklyAlarms = getAlarmsForDebug()
        weeklyAlarms = WeeklyAlarmStorage.getWeeklyAlarm()
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

    fun setWeek(weeklyAlarmId: Int, week: Int, isSet: Boolean) {
        var weeklyAlarm = weeklyAlarms.find { it.id === weeklyAlarmId }
        if (weeklyAlarm === null) {
            throw IllegalArgumentException("week is not found")
        }
        if (isSet) {
            weeklyAlarm.weeks.add(week)
        } else {
            weeklyAlarm.weeks.remove(week)
        }

        updateStorage()
    }

    fun setTimeOfDay(weeklyAlarmId: Int, timeAlarmId: Int, timeOfDay: TimeOfDay) {

    }

    fun setPower(timeAlarmId: Int, power: Boolean) {
        var timeAlarm: TimeAlarm? = null
        weeklyAlarms.forEach {
            var time = it.timeAlarms.find { it.id === timeAlarmId }
            if (time !== null) {
                timeAlarm = time
            }
        }
        if (timeAlarm === null) {
            throw IllegalArgumentException("timeAlarm is not found")
        }

        if (power) {
            timeAlarm?.powerOn()
        } else {
            timeAlarm?.powerOff()
        }

        updateStorage()
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

    private fun updateStorage() {
        WeeklyAlarmStorage.saveWeeklyAlarm(weeklyAlarms)
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
