package com.isolity.toastalarm.adapter

import android.widget.BaseAdapter
import android.content.Context

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(private val context: Context) : BaseAdapter() {

    var weeklyAlarms: List<com.isolity.toastalarm.model.WeeklyAlarm> = emptyList()

    override fun getCount(): Int = weeklyAlarms.size

    override fun getItem(position: Int): Any? = weeklyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup?): android.view.View =
            (convertView as? com.isolity.toastalarm.view.WeeklyAlarmView ?: com.isolity.toastalarm.view.WeeklyAlarmView(context)).apply {
                setWeeklyAlarm(weeklyAlarms[position])
            }
}