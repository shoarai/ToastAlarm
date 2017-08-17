package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.DailyAlarm
import com.isolity.toastalarm.model.TimeOfDay

/**
 * Created by shoarai on 2017/04/28.
 */

class TimeAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    val timeTextView: TextView by bindView(R.id.time_text_view)
    val powerSwitch: Switch by bindView(R.id.power_switch)
    val deleteButton: ImageButton by bindView(R.id.delete_button)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_time_alarm, this)
    }

    fun setTimeAlarm(dailyAlarm: DailyAlarm) {
        timeTextView.text = dailyAlarm.timeOfDay.toString()
        powerSwitch.isChecked = dailyAlarm.isPowerOn

        timeTextView.setOnClickListener {
            var timeOfDay = dailyAlarm.timeOfDay
            TimePickerManager.show(timeOfDay.hourOfDay, timeOfDay.minute, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                var newTimeOfDay = TimeOfDay(hourOfDay, minute)

                WeeklyAlarmManager.setTimeOfDay(dailyAlarm.id, newTimeOfDay)
                timeTextView.text = newTimeOfDay.toString()
            })
        }

        powerSwitch.setOnCheckedChangeListener { _, isChecked ->
            WeeklyAlarmManager.setPower(dailyAlarm.id, isChecked)
        }

        deleteButton.setOnClickListener {
            //            WeeklyAlarmManager.remove(dailyAlarmSetting.id)
        }
    }
}