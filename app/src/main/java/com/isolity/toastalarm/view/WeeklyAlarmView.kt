package com.isolity.toastalarm.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ListView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.TimeAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmView : FrameLayout {
    constructor( context: Context?) : super(context)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weekly_alarm, this)
    }

    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        var listAdapter = TimeAlarmListAdapter(context)
        listAdapter.timeAlarms = weeklyAlarm.timeAlarms.toList()

        var listView = findViewById(R.id.time_alarm_list_view) as ListView
        listView.adapter = listAdapter
    }
}