package com.isolity.toastalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by shoarai on 2017/04/17.
 */

class ToastAlarmBroadcastReceiver : BroadcastReceiver() {

    companion object{
        private val TAG = ToastAlarmBroadcastReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.v(TAG, "onReceive")

        if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
            ToastAlarmService.startAlarm(context)
        }

//// 他のアプリ更新時は対象外とする
//        if (intent.action == Intent.ACTION_PACKAGE_REPLACED) {
//            if (intent.dataString != "package:" + context.packageName) {
//                return
//            }
//        }
    }
}
