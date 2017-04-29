package com.isolity.toastalarm.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.model.TimeAlarm

/**
 * Created by shoarai on 2017/04/28.
 */

class TimeAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    val timeTextView: TextView by lazy {
        findViewById(R.id.time_text_view) as TextView
    }

    val powerSwitchView: Switch by lazy {
        findViewById(R.id.power_switch) as Switch
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_time_alarm, this)
    }

    fun setTimeAlarm(timeAlarm: TimeAlarm) {
        timeTextView.text = timeAlarm.timeOfDay.toString()
        powerSwitchView.isChecked = timeAlarm.isPowerOn
    }
}