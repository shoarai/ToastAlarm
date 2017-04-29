package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
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
}
