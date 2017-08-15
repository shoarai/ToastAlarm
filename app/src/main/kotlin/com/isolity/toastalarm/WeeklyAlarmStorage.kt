package com.isolity.toastalarm

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException
import java.util.*


/**
 * Created by shohei52a on 2017/04/30.
 */
object WeeklyAlarmStorage {
    val storageKey = "WEEKLY_ALARMS"

    var context: Context? = null

    fun saveWeeklyAlarm(weeklyAlarms: Array<WeeklyAlarm>) {
        if (context === null) {
            throw IllegalStateException("context is null")
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val jsonInstanceString = gson.toJson(weeklyAlarms)
        prefs.edit().putString(storageKey, jsonInstanceString).apply()
    }

    fun getWeeklyAlarm(): Array<WeeklyAlarm> {
        // DEBUG: ========
        return getDefaultWeeklyAlarm()
        //================


        if (context === null) {
            throw IllegalStateException("context is null")
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val weeklyAlarmsString = prefs.getString(storageKey, "")
        if (weeklyAlarmsString.isBlank()) {
            return getDefaultWeeklyAlarm()
        }
        val weeklyAlarms = gson.fromJson<Array<WeeklyAlarm>>(
                weeklyAlarmsString, Array<WeeklyAlarm>::class.java)
        return weeklyAlarms
    }

    fun getDefaultWeeklyAlarm(): Array<WeeklyAlarm> {
        var alarm1 = TimeAlarm(0, TimeOfDay(8, 0))
        alarm1.powerOn()
        var alarm2 = TimeAlarm(1, TimeOfDay(12, 0))
        alarm2.powerOn()
        var weeklyAlarm = WeeklyAlarm(0, alarm1)
        // TODO: Support multiple alarm by week.
//        weeklyAlarm.addTimeAlarm(alarm2)
        weeklyAlarm.addWeek(Calendar.MONDAY)
        weeklyAlarm.addWeek(Calendar.TUESDAY)
        weeklyAlarm.addWeek(Calendar.WEDNESDAY)
        weeklyAlarm.addWeek(Calendar.THURSDAY)
        weeklyAlarm.addWeek(Calendar.FRIDAY)

        var alarm3 = TimeAlarm(3, TimeOfDay(23, 53))
        var weeklyAlarm2 = WeeklyAlarm(1, alarm3)

        return arrayOf(weeklyAlarm, weeklyAlarm2)
    }
}