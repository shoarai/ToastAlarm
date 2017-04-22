package com.isolity.toastalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.isolity.toastalarm.model.AlarmSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        startAlarm();
    }

    private void initView() {
        AlarmSetting[] alarmSettings = AlarmSettingManager.getAlarmSettings();
        showAlarmSetting(alarmSettings);
    }

    private void startAlarm() {
        new AlarmStarter(this).startAlarm();
    }

    private void showAlarmSetting(AlarmSetting[] alarmSettings) {
        if (alarmSettings.length == 0) {
            return;
        }

        TextView timeText = (TextView) findViewById(R.id.time);
        timeText.setText(alarmSettings[0].timeOfDay.toString());
    }

}
