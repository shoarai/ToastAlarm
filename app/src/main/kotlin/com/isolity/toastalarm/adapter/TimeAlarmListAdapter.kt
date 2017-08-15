package com.isolity.toastalarm.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.model.DailyAlarm
import com.isolity.toastalarm.view.TimeAlarmView

/**
 * Created by shoarai on 2017/04/28.
 */

class TimeAlarmListAdapter(private val context: Context) : BaseAdapter() {

    var dailyAlarms: List<DailyAlarm> = emptyList()

    override fun getCount(): Int = dailyAlarms.size

    override fun getItem(position: Int): Any? = dailyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? TimeAlarmView) ?: TimeAlarmView(context)).apply {
                setTimeAlarm(dailyAlarms[position])
            }
}