package com.isolity.toastalarm.application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.isolity.toastalarm.application.`interface`.IWeeklyAlarmAppService
import com.isolity.toastalarm.application.service.WeeklyAlarmAppService
import com.isolity.toastalarm.application.view.ToastView
import com.isolity.toastalarm.domain.service.WeeklyAlarmService
import com.isolity.toastalarm.repository.WeeklyAlarmRepository


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
        startNextAlarm(context)
    }

    private fun onTimeAlarm(context: Context) {
        Log.v(TAG, "time:" + SystemClock.elapsedRealtime())
        ToastView.showToast(context)
    }

    private fun startNextAlarm(context: Context) {
        val alarms = getAppService(context).getAll()
        WeeklyAlarmManager.startNextAlarm(context, alarms)
    }

    private fun getAppService(context: Context): IWeeklyAlarmAppService {
        val repository = WeeklyAlarmRepository(context)
        val service = WeeklyAlarmService(repository)
        return WeeklyAlarmAppService(service)
    }
}