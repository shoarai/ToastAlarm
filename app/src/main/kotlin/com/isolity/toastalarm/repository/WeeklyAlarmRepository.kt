package com.isolity.toastalarm.repository

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.isolity.toastalarm.model.WeeklyAlarm


/**
 * Created by shoarai on 2017/04/30.
 */
class WeeklyAlarmRepository(private val context: Context) {
    companion object {
        private val TAG = WeeklyAlarmRepository::class.java.simpleName
        private const val storageKey = "WEEKLY_ALARMS"
    }

//    private val preference = PreferenceStore<WeeklyAlarm>(storageKey)

    /**
     * Get all weekly alarms from the stored json string.
     * @return weekly alarms
     */
    fun getAll(): Array<WeeklyAlarm>? {
//        return try {
//            preference.restore(context)
//        } catch (e: JsonSyntaxException) {
//            Log.v(TAG, e.toString())
//            null
//        }

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val stringData = prefs.getString(storageKey, "")
        if (stringData.isBlank()) {
            return null
        }

        return try {
            val type = object : TypeToken<Array<WeeklyAlarm>>() {}.type
            return Gson().fromJson<Array<WeeklyAlarm>>(stringData, type)
        } catch (e: JsonSyntaxException) {
            Log.v(TAG, e.toString())
            null
        }
    }

    /**
     * Update weekly alarms.
     * @param weeklyAlarms weeklyAlarms to store
     */
    fun update(weeklyAlarms: Array<WeeklyAlarm>) {
//        preference.save(context, weeklyAlarms) return

        val jsonInstanceString = Gson().toJson(weeklyAlarms)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(storageKey, jsonInstanceString).apply()
    }
}