package com.isolity.toastalarm.view

import android.content.Context
import android.support.v4.view.LayoutInflaterFactory
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.TextView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.model.TimeAlarm

/**
 * Created by shohei52a on 2017/04/28.
 */

class TimeAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    var timeTextView: TextView? = null
    var powerSwitchView: Switch? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_time_alarm,this)
        timeTextView = findViewById(R.id.time_text_view) as TextView
        powerSwitchView = findViewById(R.id.power_switch) as Switch
    }

    fun setTimeAlarm(timeAlarm: TimeAlarm) {
        timeTextView?.text = timeAlarm.timeOfDay.toString()
        powerSwitchView?.setChecked(timeAlarm.isPowerOn)
    }
}