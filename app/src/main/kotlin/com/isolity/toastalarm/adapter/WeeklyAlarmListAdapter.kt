package com.isolity.toastalarm.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.WeeklyAlarmDataManager
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.view.WeeklyAlarmView

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(private val context: Context) : BaseAdapter() {

    private var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()

    init {
        weeklyAlarms = WeeklyAlarmDataManager.weeklyAlarms
    }

    override fun getCount(): Int = weeklyAlarms.size

    override fun getItem(position: Int): WeeklyAlarm = weeklyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return WeeklyAlarmView(context).apply {
            setWeeklyAlarm(weeklyAlarms[position])
            onUpdate = { alarm -> update(alarm) }
            onDelete = { alarm -> delete(alarm) }
        }
    }

    fun add(weeklyAlarm: WeeklyAlarm) {
        WeeklyAlarmDataManager.add(weeklyAlarm)
        notifyDataSetChanged()
    }

    private fun update(weeklyAlarm: WeeklyAlarm) {
        WeeklyAlarmDataManager.update(weeklyAlarm)
//        notifyDataSetChanged()
    }

    private fun delete(weeklyAlarm: WeeklyAlarm) {
        WeeklyAlarmDataManager.delete(weeklyAlarm)
        notifyDataSetChanged()
    }
}
