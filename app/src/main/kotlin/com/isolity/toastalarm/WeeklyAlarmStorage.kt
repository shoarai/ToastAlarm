package com.isolity.toastalarm

import android.content.Context
import com.isolity.toastalarm.model.WeeklyAlarm
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.IllegalStateException


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
        if (context === null) {
            throw IllegalStateException("context is null")
        }
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val weeklyAlarmsString = prefs.getString(storageKey, "")
        val instance = gson.fromJson<Array<WeeklyAlarm>>(weeklyAlarmsString, Array<WeeklyAlarm>::class.java)
        return instance
    }
}