package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import com.isolity.toastalarm.R
import com.isolity.toastalarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.TimeAlarm

/**
 * Created by shoarai on 2017/04/28.
 */

class TimeAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    val timeTextView by lazy {
        findViewById(R.id.time_text_view) as TextView
    }

    val powerSwitch by lazy {
        findViewById(R.id.power_switch) as Switch
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_time_alarm, this)
    }

    fun setTimeAlarm(timeAlarm: TimeAlarm) {
        timeTextView.text = timeAlarm.timeOfDay.toString()
        powerSwitch.isChecked = timeAlarm.isPowerOn

        timeTextView.setOnClickListener {
//            var timeOfDay = timeAlarm.timeOfDay
//            var dialog = TimePickerDialog(
//                    context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
//
//            }, timeOfDay.hour, timeOfDay.minute, true)
//
//            dialog.show()
        }

        powerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            WeeklyAlarmManager.setPower(timeAlarm.id, isChecked)
        }
    }

//    private fun startAlarm() {
//        ToastAlarmService.startAlarm(applicationContext)
//        Toast.makeText(applicationContext, "Set Alarm", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun stopAlarm() {
//        ToastAlarmService.stopAlarm();
//    }
//
//    private fun showTimePickerDialog() {
//        val alarmSetting = ToastAlarmSettingManager.getFirst()
//        var time = alarmSetting.timeOfDay
//
//        val timePicker = TimePickerDialogFragment(TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
//            //時刻が選択されたときの処理
//            var time = TimeOfDay(hourOfDay, minute)
//            var alarm = TimeAlarm(time)
//            ToastAlarmSettingManager.set(alarm)
//
//        })
//
//        timePicker.show(supportFragmentManager, "timePicker")
//    }
}