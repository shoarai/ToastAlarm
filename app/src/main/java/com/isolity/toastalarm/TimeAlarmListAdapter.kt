package com.isolity.toastalarm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.view.TimeAlarmView

/**
 * Created by shoarai on 2017/04/28.
 */


class TimeAlarmListAdapter(private val context: Context) : BaseAdapter() {
    var timeAlarms:List<TimeAlarm> = emptyList()

    override fun getCount():Int = timeAlarms.size

    override fun getItem(position: Int): Any? = timeAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? TimeAlarmView) ?: TimeAlarmView(context)).apply {
                setTimeAlarm(timeAlarms[position])
            }
}