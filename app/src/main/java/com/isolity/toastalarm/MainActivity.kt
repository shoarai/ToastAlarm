package com.isolity.toastalarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.widget.TextView
import android.widget.Toast

import com.isolity.toastalarm.model.AlarmSetting
import com.isolity.toastalarm.view.TimePickerDialogFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        startAlarm()

        // DEBUG
        closeApplication()
    }

    val timeTextView: TextView by lazy {
        findViewById(R.id.time_text) as TextView
    }

    private fun initView() {
        val alarmSettings = ToastAlarmSettingManager.alarmSettings
        showAlarmSetting(alarmSettings)

        timeTextView.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun startAlarm() {
        ToastAlarmService.startAlarm(applicationContext)
//        ToastAlarmStarter(this).startAlarm()
        Toast.makeText(applicationContext, "Set Alarm", Toast.LENGTH_SHORT).show()
    }

    private fun showAlarmSetting(alarmSettings: Array<AlarmSetting>) {
        if (alarmSettings.size == 0) {
            return
        }
        timeTextView.text = alarmSettings[0].timeOfDay.toString()
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerDialogFragment()
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun closeApplication() {
    finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
