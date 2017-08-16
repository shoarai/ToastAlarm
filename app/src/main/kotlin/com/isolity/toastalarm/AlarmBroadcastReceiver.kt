package com.isolity.toastalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by shoarai on 2017/04/17.
 */

class AlarmBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = AlarmBroadcastReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.v(TAG, "onReceive")

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            Log.v(TAG, "onReceive action:android.intent.action.BOOT_COMPLETED")

            WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(context)
        }
    }
}