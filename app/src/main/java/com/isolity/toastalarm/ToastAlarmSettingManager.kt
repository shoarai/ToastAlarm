package com.isolity.toastalarm

import com.isolity.toastalarm.model.AlarmSetting
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object ToastAlarmSettingManager {
    var alarmSettings: Array<AlarmSetting>

    init {
        val alarmSetting = getNow()
        alarmSettings = arrayOf(alarmSetting)
    }

    fun set(alarmSetting: AlarmSetting) {
        alarmSettings[0] = alarmSetting
    }

    fun getFirst(): AlarmSetting = alarmSettings[0]
    fun setFirst(alarmSetting: AlarmSetting) {
        alarmSettings[0] = alarmSetting
    }

    fun getNext(): AlarmSetting? {
        var nextAlarm  = null
        alarmSettings.forEach { alarmSetting->

        }

//        val now = Calendar.getInstance()
//        if (calendar.before(now)) {
//            calendar.add(Calendar.DAY_OF_YEAR, 1);
//        }

        return  nextAlarm
    }

    private  fun getNow(): AlarmSetting {
        val alarmSetting = AlarmSetting()
        val calendar = Calendar.getInstance()
        alarmSetting.timeOfDay =
                TimeOfDay(calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE))
        return alarmSetting
    }
}
