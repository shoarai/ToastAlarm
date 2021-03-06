package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.entity.DailyAlarm
import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object NextAlarmFilter {
    /**
     * Get a calendar of next alarm from now.
     * @param weeklyAlarms weekly alarms
     */
    fun getNextAlarmCalendar(weeklyAlarms: List<WeeklyAlarm>): Calendar? {
        val now = Calendar.getInstance()
        now.add(Calendar.MINUTE, 1)
        now.set(Calendar.SECOND, 0)
        now.set(Calendar.MILLISECOND, 0)

        var earliest: Calendar? = null
        weeklyAlarms.forEach { weeklyAlarm ->
            toCalendarAfterNow(weeklyAlarm, now).let {
                it?.run {
                    if (earliest == null || it.before(earliest)) {
                        earliest = it
                    }
                }
            }
        }
        return earliest
    }

    private fun toCalendarAfterNow(weeklyAlarm: WeeklyAlarm, now: Calendar): Calendar? {
        if (weeklyAlarm.weeks.isEmpty()) {
            return null
        }
        return toCalendarAfterNow(weeklyAlarm.dailyAlarms, weeklyAlarm.weeks, now)
    }

    private fun toCalendarAfterNow(dailyAlarms: List<DailyAlarm>, weeks: Set<Int>, now: Calendar): Calendar? {
        var earliest: Calendar? = null
        dailyAlarms.filter { it.isPowerOn }.forEach { dailyAlarms ->
            weeks.forEach { week ->
                toCalendarAfterNow(dailyAlarms.timeOfDay, week, now).let {
                    if (earliest == null || it.before(earliest)) {
                        earliest = it
                    }
                }
            }
        }
        return earliest
    }

    private fun toCalendarAfterNow(timeOfDay: TimeOfDay, week: Int, now: Calendar): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, week)
        calendar.set(Calendar.HOUR_OF_DAY, timeOfDay.hourOfDay)
        calendar.set(Calendar.MINUTE, timeOfDay.minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (calendar.before(now)) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }
        return calendar
    }
}
