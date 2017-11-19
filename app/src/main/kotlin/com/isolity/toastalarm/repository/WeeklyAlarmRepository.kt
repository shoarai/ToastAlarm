package com.isolity.toastalarm.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.isolity.toastalarm.model.WeeklyAlarm


/**
 * Created by shoarai on 2017/04/30.
 */
object WeeklyAlarmRepository {
    private val TAG = WeeklyAlarmRepository::class.java.simpleName
    private const val storageKey = "WEEKLY_ALARMS"

    private val preference = PreferenceStorage<WeeklyAlarm>(storageKey)

    /**
     * Get all weekly alarms from the stored json string.
     * @return weekly alarms
     */
    fun getAll(context: Context): Array<WeeklyAlarm>? {
        return try {
            preference.restore(context)
        } catch (e: JsonSyntaxException) {
            Log.v(TAG, e.toString())
            null
        }
    }

    /**
     * Update weekly alarms.
     * @param weeklyAlarms weeklyAlarms to store
     */
    fun update(context: Context, weeklyAlarms: Array<WeeklyAlarm>) {
        preference.save(context, weeklyAlarms)
    }
}