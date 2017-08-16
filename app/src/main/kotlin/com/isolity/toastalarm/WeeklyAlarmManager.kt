package com.isolity.toastalarm

import android.content.Context
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.isolity.toastalarm.model.DailyAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.model.WeeklyAlarmList
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object WeeklyAlarmManager {
    private val TAG = WeeklyAlarmManager::class.java.simpleName

    var weeklyAlarmList: WeeklyAlarmList

    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()
        get () {
            return weeklyAlarmList.weeklyAlarms
        }

    init {
        var weeklyAlarms: Array<WeeklyAlarm>
        try {
            var alarmNullable = WeeklyAlarmStorage.restore()
            weeklyAlarms = alarmNullable ?: WeeklyAlarmCreator.createDefaultWeeklyAlarms()
        } catch (e: JsonSyntaxException) {
            Log.v(TAG, e.toString())
            weeklyAlarms = WeeklyAlarmCreator.createDefaultWeeklyAlarms()
            updateStorage()
        }

        // DEBUG: Create test data ====
//        weeklyAlarms = getTestAlarm()
        // ============================

        weeklyAlarmList = WeeklyAlarmList(weeklyAlarms)
    }

    private fun getTestAlarm(): Array<WeeklyAlarm> {
        var alarm1 = DailyAlarm(0, TimeOfDay(8, 0))
        alarm1.powerOn()
        var alarm2 = DailyAlarm(1, TimeOfDay(12, 0))
        alarm2.powerOn()
        var weeklyAlarm = WeeklyAlarm(0, alarm1)
        // TODO: Support multiple alarm by week.
//        weeklyAlarm.addTimeAlarm(alarm2)
        weeklyAlarm.addWeek(
                Calendar.MONDAY, Calendar.TUESDAY,
                Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY
        )

        var alarm3 = DailyAlarm(3, TimeOfDay(23, 53))
        var weeklyAlarm2 = WeeklyAlarm(1, alarm3)

        return arrayOf(weeklyAlarm, weeklyAlarm2)
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
        WeeklyAlarmStorage.save(weeklyAlarmList.weeklyAlarms.toTypedArray())
        if (context !== null) {
            WeeklyAlarmServiceManager.startAlarm(context as Context)
        }

        // DEBUG:
        update?.invoke()
    }
}
