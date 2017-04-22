package com.isolity.toastalarm;

import com.isolity.toastalarm.model.AlarmSetting;
import com.isolity.toastalarm.model.TimeOfDay;

import java.util.Date;

/**
 * Created by shohei52a on 2017/04/22.
 */

 public class AlarmSettingManager {
    static public AlarmSetting[] getAlarmSettings() {
        final AlarmSetting alarmSetting = new AlarmSetting();
        alarmSetting.timeOfDay = new TimeOfDay(8,54);

        AlarmSetting[] alarmSettings = new AlarmSetting[]{
            alarmSetting
        };

        return alarmSettings;
    }
}
