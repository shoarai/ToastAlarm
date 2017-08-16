package com.isolity.toastalarm

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.isolity.toastalarm.model.WeeklyAlarm
import java.lang.IllegalStateException


/**
 * Created by shoarai on 2017/04/30.
 */
object WeeklyAlarmStorage {
    const val storageKey = "WEEKLY_ALARMS"

    var context: Context? = null

    /**
     * Store weekly alarms.
     * @param weeklyAlarms weeklyAlarms to store
     */
    fun store(weeklyAlarms: Array<WeeklyAlarm>) {
        if (context === null) {
            throw IllegalStateException("context is null")
        }
        val gson = Gson()
        val jsonInstanceString = gson.toJson(weeklyAlarms)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(storageKey, jsonInstanceString).apply()
    }

    /**
     * Restore weekly alarms from the stored json string.
     * @return weekly alarms
     * @throws JsonSyntaxException if json is not a valid representation for weekly alarms
     */
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
}