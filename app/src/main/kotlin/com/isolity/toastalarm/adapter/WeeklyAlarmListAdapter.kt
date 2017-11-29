package com.isolity.toastalarm.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.isolity.toastalarm.WeeklyAlarmDataManager
import com.isolity.toastalarm.alarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.model.WeeklyAlarmCreator
import com.isolity.toastalarm.repository.WeeklyAlarmRepository
import com.isolity.toastalarm.view.WeeklyAlarmView

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(private val context: Context) : BaseAdapter() {

    private var weeklyAlarms: List<WeeklyAlarm> = emptyList()

    init {
        WeeklyAlarmDataManager.init(context)
        WeeklyAlarmDataManager.onUpdate = { weeklyAlarms -> onUpdateWeeklyAlarm(weeklyAlarms) }
        weeklyAlarms = WeeklyAlarmDataManager.getAll()
    }

    private fun onUpdateWeeklyAlarm(weeklyAlarms: Array<WeeklyAlarm>) {
        WeeklyAlarmRepository.update(context, weeklyAlarms)
        WeeklyAlarmManager.startNextAlarm(context)
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

    fun add(timeOfDay: TimeOfDay) {
        val newAlarm = WeeklyAlarmCreator.createWeeklyAlarm(weeklyAlarms.toTypedArray())
        newAlarm.dailyAlarms.first().timeOfDay = timeOfDay

        WeeklyAlarmDataManager.add(newAlarm)
        notifyDataSetChanged()
    }

    private fun update(weeklyAlarm: WeeklyAlarm) {
        WeeklyAlarmDataManager.update(weeklyAlarm)
    }

    private fun delete(weeklyAlarm: WeeklyAlarm) {
        WeeklyAlarmDataManager.delete(weeklyAlarm)
        notifyDataSetChanged()
    }
}
