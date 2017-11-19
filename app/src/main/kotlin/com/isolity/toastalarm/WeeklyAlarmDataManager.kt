package com.isolity.toastalarm

import android.content.Context
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.model.WeeklyAlarmUtil
import com.isolity.toastalarm.repository.WeeklyAlarmRepository

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmDataManager {
//    private val TAG = WeeklyAlarmManager::class.java.simpleName

    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()
        private set

//    @Inject
//    lateinit var weeklyAlarms: MutableList<WeeklyAlarm>
//        private set

    private var hasInit = false

    fun init(context: Context) {
        if (hasInit) return
        hasInit = true

        val alarm = WeeklyAlarmRepository.getAll(context)
        if (alarm != null) {
            weeklyAlarms = alarm.toMutableList()
            return
        }
        val defaultValue = emptyArray<WeeklyAlarm>()
        WeeklyAlarmRepository.update(context, defaultValue)
        weeklyAlarms = defaultValue.toMutableList()
    }

    fun getById(id: Int): WeeklyAlarm {
        return weeklyAlarms.single { it.id == id }
    }

    fun getAll(): List<WeeklyAlarm> {
        return weeklyAlarms
    }

    fun add(alarm: WeeklyAlarm) {
        weeklyAlarms.add(alarm)
    }

    fun update(alarm: WeeklyAlarm) {
        val i = weeklyAlarms.indexOfFirst { it.id == alarm.id }
        if (i == -1) throw IllegalArgumentException("Not found WeeklyAlarm of ID")
        weeklyAlarms[i] = alarm
    }

    fun delete(alarm: WeeklyAlarm) {
        weeklyAlarms.remove(alarm)
    }

    fun addWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarms.add(weeklyAlarm)
        onUpdateData()
    }

    fun remove(weeklyAlarmId: Int) {
        weeklyAlarms.removeAll { it.id == weeklyAlarmId }
        onUpdateData()
    }

    fun setWeek(weeklyAlarmId: Int, week: Int, isSet: Boolean) {
        weeklyAlarms.single { it.id == weeklyAlarmId }.apply {
            if (isSet) {
                addWeek(week)
            } else {
                removeWeek(week)
            }
        }

        onUpdateData()
    }

    fun setTimeOfDay(timeAlarmId: Int, timeOfDay: TimeOfDay) {
        WeeklyAlarmUtil.findTimeAlarm(weeklyAlarms, timeAlarmId).timeOfDay = timeOfDay
        onUpdateData()
    }

    fun setPower(timeAlarmId: Int, power: Boolean) {
        WeeklyAlarmUtil.findTimeAlarm(weeklyAlarms, timeAlarmId).isPowerOn = power
        onUpdateData()
    }

    var onUpdate: ((Array<WeeklyAlarm>) -> Unit)? = null

    private fun onUpdateData() {
        onUpdate?.invoke(weeklyAlarms.toTypedArray())
    }
}
