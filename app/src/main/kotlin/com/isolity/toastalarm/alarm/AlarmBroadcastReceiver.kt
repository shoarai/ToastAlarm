package com.isolity.toastalarm.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.isolity.toastalarm.WeeklyAlarmDataManager
import com.isolity.toastalarm.view.ToastView


/**
 * Created by shoarai on 2017/04/17.
 */

class AlarmBroadcastReceiver : BroadcastReceiver() {
    companion object {
        private val TAG = AlarmBroadcastReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.v(TAG, "onReceive action:" + intent.action)

        if (intent.action == null) {
            onTimeAlarm(context)
        }

        WeeklyAlarmDataManager.init(context)
        WeeklyAlarmManager.startNextAlarm(context)
    }

    private fun onTimeAlarm(context: Context) {
        Log.v(TAG, "time:" + SystemClock.elapsedRealtime())
        ToastView.showToast(context)
    }
}