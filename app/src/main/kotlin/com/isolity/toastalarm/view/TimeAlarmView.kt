package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay

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
            var timeOfDay = timeAlarm.timeOfDay
            TimePickerManager.show(timeOfDay.hour, timeOfDay.minute, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                var newTimeOfDay = TimeOfDay(hourOfDay, minute)
                WeeklyAlarmManager.setTimeOfDay(timeAlarm.id, newTimeOfDay)
                timeTextView.text = newTimeOfDay.toString()
            })
        }

        powerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            WeeklyAlarmManager.setPower(timeAlarm.id, isChecked)
        }
    }
}