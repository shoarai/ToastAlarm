package com.isolity.toastalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast

import java.util.Calendar

import android.content.Context.ALARM_SERVICE
import android.os.SystemClock
import android.util.Log

/**
 * Created by shoarai on 2017/04/17.
 */

class ToastAlarmStarter internal constructor(internal var context: Context) {
//
//    fun startAlarm() {
//        Log.v(TAG, "startAlarm")
//
//        // 時間をセットする
//        val calendar = Calendar.getInstance()
//        // Calendarを使って現在の時間をミリ秒で取得
//        calendar.timeInMillis = System.currentTimeMillis()
//        // 5秒後に設定
//        calendar.add(Calendar.SECOND, 10)
//
//        val alarmSender = getPendingIntent()
//
//        // アラームをセットする
//        val am = context.getSystemService(ALARM_SERVICE) as AlarmManager
////        am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmSender)
//
//        am.setRepeating(
//                AlarmManager.RTC_WAKEUP,
//                SystemClock.elapsedRealtime(),
//                5*1000, // 1sec = 1000
//                alarmSender
//        );
//
//    }
//
//
//    private fun getPendingIntent(): PendingIntent {
////        val intent = Intent(context.applicationContext, ToastAlarmService::class.java)
//        val intent = Intent(context.applicationContext, ToastAlarmBroadcastReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context.applicationContext, 0, intent, 0)
//        return pendingIntent
//    }
//
//    private val TAG = ToastAlarmStarter::class.java.simpleName
}
