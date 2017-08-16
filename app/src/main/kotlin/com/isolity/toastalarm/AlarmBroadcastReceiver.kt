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
        Log.v(TAG, "onReceive action:" + intent.action )

        ToastService.showDebugToast(context, "onReceive action:" + intent.action)

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(context)
        }
    }
}