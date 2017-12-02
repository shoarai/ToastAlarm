package com.isolity.toastalarm.application.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.application.`interface`.IWeeklyAlarmAppService
import com.isolity.toastalarm.application.view.WeeklyAlarmView
import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(
        private val context: Context,
        private val weeklyAlarmService: IWeeklyAlarmAppService) : BaseAdapter() {

    private val weeklyAlarms = weeklyAlarmService.getAll()

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

    fun add(timeOfDay: TimeOfDay) {
        weeklyAlarmService.create(timeOfDay)
        notifyDataSetChanged()
        dispatchOnChangeAlarms()
    }

    private fun update(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmService.update(weeklyAlarm)
        dispatchOnChangeAlarms()
    }

    private fun delete(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmService.delete(weeklyAlarm)
        notifyDataSetChanged()
        dispatchOnChangeAlarms()
    }

    private fun dispatchOnChangeAlarms() {
        onChangeAlarms?.invoke(weeklyAlarms.toList())
    }
}