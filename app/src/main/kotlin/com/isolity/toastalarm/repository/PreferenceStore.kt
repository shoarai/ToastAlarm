package com.isolity.toastalarm.repository

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

/**
 * Created by shoarai on 2017/04/30.
 */
class PreferenceStore<T>(private val storageKey: String) {
    /**
     * Save data.
     * @param context Context
     * @param data Data
     */
    fun save(context: Context, data: Array<T>) {
        val gson = Gson()
        val jsonInstanceString = gson.toJson(data)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(storageKey, jsonInstanceString).apply()
    }

    /**
     * Restore data from the stored json string.
     * @param context Context
     * @throws JsonSyntaxException if json is not a valid representation for weekly alarms
     * @return Stored data
     */
    fun restore(context: Context): Array<T>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val stringData = prefs.getString(storageKey, "")
        if (stringData.isBlank()) {
            return null
        }

        val gson = Gson()
        val type = object : TypeToken<Array<T>>() {}.type
        return gson.fromJson<Array<T>>(stringData, type)
    }
}