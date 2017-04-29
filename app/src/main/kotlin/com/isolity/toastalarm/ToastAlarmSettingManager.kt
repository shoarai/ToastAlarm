package com.isolity.toastalarm

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object ToastAlarmSettingManager {
    var timeAlarms: Array<TimeAlarm>

    init {
        val alarmSetting = getNow()
        timeAlarms = arrayOf(alarmSetting)
    }

    fun getFirst(): TimeAlarm = timeAlarms[0]

    fun getNow(): TimeAlarm {
        val calendar = Calendar.getInstance()
        var timeOfDay = TimeOfDay(
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE))
        val alarmSetting = TimeAlarm(timeOfDay)
        return alarmSetting
    }
}
