package com.isolity.toastalarm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.view.WeeklyAlarmView

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(private val context: Context) : BaseAdapter() {

    var weeklyAlarms: List<WeeklyAlarm> = emptyList()

    override fun getCount(): Int = weeklyAlarms.size

    override fun getItem(position: Int): Any? = weeklyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            (convertView as? WeeklyAlarmView ?: WeeklyAlarmView(context)).apply {
                setWeeklyAlarm(weeklyAlarms[position])
            }
}