package com.isolity.toastalarm.adapter

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

    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()

    override fun getCount(): Int = weeklyAlarms.size

    override fun getItem(position: Int): Any? = weeklyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            (convertView as? WeeklyAlarmView ?: WeeklyAlarmView(context)).apply {
                setWeeklyAlarm(weeklyAlarms[position])
            }

    fun add(weeklyAlarm: WeeklyAlarm): Boolean {
        val ress = weeklyAlarms.add(weeklyAlarm)
        if (ress) {
            notifyDataSetChanged()
        }
        return ress
    }

    fun remove(weeklyAlarm: WeeklyAlarm): Boolean {
        val ress = weeklyAlarms.remove(weeklyAlarm)
        if (ress) {
            notifyDataSetChanged()
        }
        return ress
    }
}
