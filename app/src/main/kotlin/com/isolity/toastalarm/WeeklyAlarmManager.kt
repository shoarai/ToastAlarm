package com.isolity.toastalarm

import android.content.Context
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.model.WeeklyAlarmList

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmManager {
    var weeklyAlarmList: WeeklyAlarmList

    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()
        get () {
            return weeklyAlarmList.weeklyAlarms
        }

    init {
        val weeklyAlarms = WeeklyAlarmStorage.getWeeklyAlarm().toMutableList()
        weeklyAlarmList = WeeklyAlarmList(weeklyAlarms.toTypedArray())
    }

    fun addWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarmList.weeklyAlarms.add(weeklyAlarm)
        updateStorage()
    }

    fun removeByTimeAlarmId(timeAlarmId: Int) {
        // TODO: Support multiple timealarm
        val alarmToDelete = weeklyAlarmList.findWeeklyAlarmByTimeAlarmId(timeAlarmId)
        weeklyAlarmList.weeklyAlarms.remove(alarmToDelete)

        updateStorage()
    }

    fun remove(weeklyAlarmId: Int) {
        val alarmToDeleted = weeklyAlarmList.findWeeklyAlarm(weeklyAlarmId)
        weeklyAlarmList.weeklyAlarms.remove(alarmToDeleted)

        updateStorage()
    }

    fun hasPowerOn(): Boolean {
        return weeklyAlarmList.hasPowerOn()
    }

    fun setWeek(weeklyAlarmId: Int, week: Int, isSet: Boolean) {
        weeklyAlarmList.findWeeklyAlarm(weeklyAlarmId).apply {
            if (isSet) {
                addWeek(week)
            } else {
                removeWeek(week)
            }
        }

        updateStorage()
    }

    fun setTimeOfDay(timeAlarmId: Int, timeOfDay: TimeOfDay) {
        weeklyAlarmList.findTimeAlarm(timeAlarmId).timeOfDay = timeOfDay

        updateStorage()
    }

    fun setPower(timeAlarmId: Int, power: Boolean) {
        weeklyAlarmList.findTimeAlarm(timeAlarmId).isPowerOn = power

        updateStorage()
    }

    var context: Context? = null

    // DEBUG: ========
    var update: (() -> Unit)? = null
    // ===============

    private fun updateStorage() {
        WeeklyAlarmStorage.saveWeeklyAlarm(weeklyAlarmList.weeklyAlarms.toTypedArray())
        if (context !== null) {
            WeeklyAlarmServiceManager.startAlarm(context as Context)
        }

        // DEBUG:
        update?.invoke()
    }
}
