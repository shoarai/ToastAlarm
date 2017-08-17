package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmFilter {
    /**
     * Get a calendar of next time from now from weekly alarm list.
     * @param weeklyAlarms weekly alarms
     */
    fun getNextAlarmAsCalendar(weeklyAlarms: Array<WeeklyAlarm>): Calendar {
        var calendar: Calendar? = null

        weeklyAlarms.forEach {
            var cal = getNextAlarmAsCalendar(it)
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

    private fun getNextAlarmAsCalendar(weeklyAlarm: WeeklyAlarm): Calendar? {
        var calendar: Calendar? = null

        weeklyAlarm.weeks.forEach { week ->
            weeklyAlarm.dailyAlarms
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
        calendar.set(Calendar.HOUR_OF_DAY, timeOfDay.hourOfDay)
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
