package com.isolity.toastalarm.repository

import android.content.Context
import com.isolity.toastalarm.domain.`interface`.IWeeklyAlarmRepository
import com.isolity.toastalarm.domain.entity.WeeklyAlarm

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarmRepository(context: Context) : IWeeklyAlarmRepository {
    private val weeklyAlarmRepository = WeeklyAlarmStore(context)

    private val weeklyAlarmsCache: MutableList<WeeklyAlarm> by lazy {
        val alarm = weeklyAlarmRepository.restore()
        alarm?.toMutableList() ?: mutableListOf()
    }

    override fun getById(id: Int): WeeklyAlarm {
        return weeklyAlarmsCache.single { it.id == id }
    }

    override fun getAll(): List<WeeklyAlarm> {
        return weeklyAlarmsCache
    }

    override fun add(alarm: WeeklyAlarm) {
        weeklyAlarmsCache.add(alarm)
        onUpdateData()
    }

    override fun update(alarm: WeeklyAlarm) {
        val i = weeklyAlarmsCache.indexOfFirst { it.id == alarm.id }
        if (i == -1) throw IllegalArgumentException("Not found WeeklyAlarm of ID")
        weeklyAlarmsCache[i] = alarm
        onUpdateData()
    }

    override fun delete(alarm: WeeklyAlarm) {
        weeklyAlarmsCache.remove(alarm)
        onUpdateData()
    }

    private fun onUpdateData() {
        weeklyAlarmRepository.save(weeklyAlarmsCache.toTypedArray())
    }
}
