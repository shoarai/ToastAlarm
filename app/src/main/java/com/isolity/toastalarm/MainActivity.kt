package com.isolity.toastalarm

import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.widget.*
import com.isolity.toastalarm.ToastAlarmSettingManager.alarmSettings

import com.isolity.toastalarm.model.AlarmSetting
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.view.TimePickerDialogFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        // DEBUG
//        closeApplication()
    }

    override fun onStart() {
        super.onStart()

        showAlarmSetting()
    }

    val timeTextView: TextView by lazy {
        findViewById(R.id.time_text) as TextView
    }

    val startAlarmSwitch: Switch by lazy {
        findViewById(R.id.start_alarm_switch) as Switch
    }


    private fun initView() {
        timeTextView.setOnClickListener {
            showTimePickerDialog()
        }

        startAlarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startAlarm()
            } else {
                stopAlarm();
            }
        }
    }

    private fun showAlarmSetting() {
        val alarmSettings = alarmSettings
        showAlarmSetting(alarmSettings)
    }

    private fun startAlarm() {
        ToastAlarmService.startAlarm(applicationContext)
//        ToastAlarmStarter(this).startAlarm()
        Toast.makeText(applicationContext, "Set Alarm", Toast.LENGTH_SHORT).show()
    }

    private fun stopAlarm() {
        ToastAlarmService.stopAlarm();
    }

    private fun showAlarmSetting(alarmSettings: Array<AlarmSetting>) {
        if (alarmSettings.size == 0) {
            return
        }
        timeTextView.text = alarmSettings[0].timeOfDay.toString()
    }

    private fun showTimePickerDialog() {
        val alarmSetting = ToastAlarmSettingManager.getFirst()
        var time = alarmSetting.timeOfDay

        val timePicker = TimePickerDialogFragment(TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            //時刻が選択されたときの処理
            var time = TimeOfDay(hourOfDay, minute)
            var alarm = AlarmSetting()
            alarm.timeOfDay = time
            ToastAlarmSettingManager.set(alarm)

            showAlarmSetting(alarmSettings)
        })

        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
