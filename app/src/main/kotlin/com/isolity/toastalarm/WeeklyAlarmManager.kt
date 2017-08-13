package com.isolity.toastalarm

import android.content.Context
import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmManager {
    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()
        private set

    init {
        weeklyAlarms = WeeklyAlarmStorage.getWeeklyAlarm().toMutableList()
    }

    fun addWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarms.add(weeklyAlarm)
        updateStorage()
    }

    fun createWeeklyAlarmId(): Int {
        for (i in 0..Int.MAX_VALUE) {
            if (!weeklyAlarms.any { it.id === i }) {
                continue
            }
            return i
        }
        throw IllegalAccessException("Can't create unique id")
    }

    fun createTimeAlarmId(): Int {
        for (i in 0..Int.MAX_VALUE) {
            if (!weeklyAlarms.any { it.timeAlarms.any { it.id === i } }) {
                continue
            }
            return i
        }
        throw IllegalAccessException("Can't create unique id")
    }

    fun hasPowerOn(): Boolean {
        weeklyAlarms
                .filter { it.weeks.isNotEmpty() }
                .forEach {
                    it.timeAlarms.forEach {
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

    fun setTimeOfDay(timeAlarmId: Int, timeOfDay: TimeOfDay) {
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

        timeAlarm?.timeOfDay = timeOfDay
        updateStorage()
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
            throw IllegalStateException("Alarm on power is not found")
        }
        return calendar as Calendar
    }

    var context: Context? = null

    private fun updateStorage() {
        WeeklyAlarmStorage.saveWeeklyAlarm(weeklyAlarms.toTypedArray())
        if (context !== null) {
            WeeklyAlarmServiceManager.startAlarm(context as Context)
        }
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
        now.set(Calendar.SECOND, 0)
        now.add(Calendar.MINUTE, 1)
        if (calendar.before(now)) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        return calendar
    }
}
