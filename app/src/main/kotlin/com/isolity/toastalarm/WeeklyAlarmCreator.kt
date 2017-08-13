package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.util.*

/**
 * Created by shohei52a on 2017/08/13.
 */

object WeeklyAlarmCreator {
    /**
     * Create weekly alarm for current time.
     */
     fun createWeeklyAlarm(): WeeklyAlarm {
        var c = Calendar.getInstance()
        var timeOfDay = TimeOfDay(
                c.get((Calendar.HOUR_OF_DAY)),
                c.get((Calendar.MINUTE)))
        var timeAlarm = TimeAlarm(WeeklyAlarmManager.createTimeAlarmId(), timeOfDay)
        var weeklyAlarm = WeeklyAlarm(WeeklyAlarmManager.createWeeklyAlarmId(), timeAlarm)
        return weeklyAlarm
    }
}