package com.isolity.toastalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.SystemClock
import android.util.Log

/**
 * Created by shoarai on 2017/04/17.
 */

class AlarmBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = AlarmBroadcastReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.v(TAG, "onReceive action:" + intent.action)

        when (intent.action) {
            null ->
                Handler().post({
                    Log.v(TAG, "time:" + SystemClock.elapsedRealtime())

                    ToastService.showToast(context)
                    WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(context)
                })

            "android.intent.action.BOOT_COMPLETED" ->
                WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(context)
        }
    }


//    private var mHandler: Handler = Handler()
//
//    fun onTime(intent: Intent) {
//        Log.v(TAG, "onHandleIntent!!!")
//
//        mHandler.post({
//            Log.v(AlarmService.TAG, "time:" + SystemClock.elapsedRealtime())
//
//            ToastService.showToast(applicationContext)
//            WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(applicationContext)
//        })
//    }
}