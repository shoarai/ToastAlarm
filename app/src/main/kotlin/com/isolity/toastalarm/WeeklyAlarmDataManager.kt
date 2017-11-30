package com.isolity.toastalarm

import android.content.Context
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.repository.WeeklyAlarmRepository

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarmDataManager(context: Context) {
    private val weeklyAlarmRepository = WeeklyAlarmRepository(context)

    private val weeklyAlarms: MutableList<WeeklyAlarm> by lazy {
        val alarm = weeklyAlarmRepository.getAll()
        alarm?.toMutableList() ?: mutableListOf()
    }

    fun getById(id: Int): WeeklyAlarm {
        return weeklyAlarms.single { it.id == id }
    }

    fun getAll(): List<WeeklyAlarm> {
        return weeklyAlarms
    }

    fun add(alarm: WeeklyAlarm) {
        weeklyAlarms.add(alarm)
        onUpdateData()
    }

    fun update(alarm: WeeklyAlarm) {
        val i = weeklyAlarms.indexOfFirst { it.id == alarm.id }
        if (i == -1) throw IllegalArgumentException("Not found WeeklyAlarm of ID")
        weeklyAlarms[i] = alarm
        onUpdateData()
    }

    fun delete(alarm: WeeklyAlarm) {
        weeklyAlarms.remove(alarm)
        onUpdateData()
    }

    private fun onUpdateData() {
        weeklyAlarmRepository.update(weeklyAlarms.toTypedArray())
    }
}
