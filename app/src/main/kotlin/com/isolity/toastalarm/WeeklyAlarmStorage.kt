package com.isolity.toastalarm

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException


/**
 * Created by shohei52a on 2017/04/30.
 */
object WeeklyAlarmStorage {
    val storageKey = "WEEKLY_ALARMS"

    var context: Context? = null

    fun save(weeklyAlarms: Array<WeeklyAlarm>) {
        if (context === null) {
            throw IllegalStateException("context is null")
        }
        val gson = Gson()
        val jsonInstanceString = gson.toJson(weeklyAlarms)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(storageKey, jsonInstanceString).apply()
    }

    fun restore(): Array<WeeklyAlarm>? {
        if (context === null) {
            throw IllegalStateException("context is null")
        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val weeklyAlarmsString =  prefs.getString(storageKey, "")
        if (weeklyAlarmsString.isBlank()) {
            return null
        }
        val gson = Gson()

        try {
            val weeklyAlarms = gson.fromJson<Array<WeeklyAlarm>>(
                    weeklyAlarmsString, Array<WeeklyAlarm>::class.java)
            return weeklyAlarms
        } catch (e: JsonSyntaxException) {
            throw e
        }
    }

    private fun restoreString():String{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(storageKey, "")
    }
}