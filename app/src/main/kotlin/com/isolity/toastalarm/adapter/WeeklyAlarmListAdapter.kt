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

class WeeklyAlarmListAdapter(
        private val context: Context,
        private val weeklyAlarmDataManager: WeeklyAlarmDataManager) : BaseAdapter() {

    private val weeklyAlarms = weeklyAlarmDataManager.getAll()

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

    var onChangeAlarms: ((weeklyAlarms: List<WeeklyAlarm>) -> Unit)? = null

    fun add(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmDataManager.add(weeklyAlarm)
        notifyDataSetChanged()
        dispatchOnChangeAlarms()
    }

    private fun update(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmDataManager.update(weeklyAlarm)
        dispatchOnChangeAlarms()
    }

    private fun delete(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmDataManager.delete(weeklyAlarm)
        notifyDataSetChanged()
        dispatchOnChangeAlarms()
    }

    private fun dispatchOnChangeAlarms() {
        onChangeAlarms?.invoke(weeklyAlarms.toList())
    }
}
