package com.isolity.toastalarm.application.library

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.isolity.toastalarm.application.AlarmBroadcastReceiver
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

object OnceAlarmManager {
    private const val REQUEST_CODE = 0
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null
    private var receiver = AlarmBroadcastReceiver::class

    /**
     * Start an alarm.
     * Stop the previous alarm.
     * @param context Context
     * @param calendar Time that the alarm will go off.
     */
    fun startAlarm(context: Context, calendar: Calendar) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, receiver.java)
        pendingIntent = PendingIntent.getBroadcast(
                context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager?.setExact(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
    }

    /**
     * Stop the alarm if there is the started alarm.
     */
    fun stopAlarm() {
        alarmManager?.cancel(pendingIntent)
    }
}