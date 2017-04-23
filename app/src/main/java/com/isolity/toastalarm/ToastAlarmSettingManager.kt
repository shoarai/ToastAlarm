package com.isolity.toastalarm

import com.isolity.toastalarm.model.AlarmSetting
import com.isolity.toastalarm.model.TimeOfDay

import java.util.Date

/**
 * Created by shohei52a on 2017/04/22.
 */

object ToastAlarmSettingManager {
    val alarmSettings: Array<AlarmSetting>
        get() {
            val alarmSetting = AlarmSetting()
            alarmSetting.timeOfDay = TimeOfDay(8, 54)

            val alarmSettings = arrayOf(alarmSetting)

            return alarmSettings
        }
}
