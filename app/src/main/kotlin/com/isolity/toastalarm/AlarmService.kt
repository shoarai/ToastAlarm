package com.isolity.toastalarm

import android.content.Intent;
import android.util.Log;
import android.widget.Toast
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.os.Handler
import android.app.AlarmManager
import android.os.SystemClock
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/04/22.
 */

class AlarmService : IntentService("AlarmService") {

    private var mHandler: Handler = Handler()

    override fun onHandleIntent(intent: Intent) {
        Log.v(TAG, "onHandleIntent!!!")

        mHandler.post({
            Log.v(TAG, "time:" + SystemClock.elapsedRealtime())

            ToastService.showToast(applicationContext)
            WeeklyAlarmServiceManager.startNextAlarmWithPowerOn(applicationContext)
        })
    }

    companion object {
        // This value is defined and consumed by app code, so any value will work.
        // There's no significance to this sample using 0.
        private val REQUEST_CODE = 0
        private val TAG = AlarmService::class.java.simpleName

        private var alarmMgr: AlarmManager? = null
        private var alarmIntent: PendingIntent? = null

        /**
         * Start alarm.
         * @param context context
         * @param calendar calendar
         */
        fun startAlarm(context: Context, calendar: Calendar) {
            alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmService::class.java)
            alarmIntent = PendingIntent.getService(
                    context, REQUEST_CODE, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT)

            alarmMgr?.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, alarmIntent)
//            alarmMgr?.setExact(AlarmManager.RTC, calendar.timeInMillis, alarmIntent)

            // HACK: Use BroadCastReceiver not Service
//            alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val intent = Intent(context, AlarmBroadcastReceiver::class.java)
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
//            alarmMgr?.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis, alarmIntent);

            ToastService.showDebugToast(context, "startAlarm" + calendar.toString())
        }

        /**
         * Stop alarm.
         */
        fun stopAlarm() {
            alarmMgr?.cancel(alarmIntent)
        }
    }
}

//class ToastAlarmService : Service() {
//
//    override fun onBind(intent: Intent): IBinder? {
//        return null
//    }
//
//    override fun onCreate() {
//        val thr = Thread(null, mTask, "ToastAlarmServiceThread")
//        thr.start()
//        Log.v(TAG, "onCreate")
//    }
//
//    // アラーム用サービス
//    internal var mTask: Runnable = Runnable {
//        // アラームを受け取るActivityを指定
//        val alarmBroadcast = Intent()
//        // ここでActionをセットする(Manifestに書いたものと同じであれば何でもよい)
//        alarmBroadcast.action = "ToastAlarmAction"
//        // レシーバーへ渡す
//        sendBroadcast(alarmBroadcast)
//        // 役目を終えたサービスを止める
//        this@ToastAlarmService.stopSelf()
//        Log.v(TAG, "サービス停止")
//    }
//
//    private val TAG = ToastAlarmService::class.java.simpleName
//}


//class ToastAlarmService : IntentService("ToastAlarmService") {
//
//    override fun onHandleIntent(intent: Intent?) {
//        try {
//            Toast.makeText(this, "ToastAlarmService.Received", Toast.LENGTH_LONG).show()
//            // サービス内部の処理
//        } finally {
//            // Wakelockの解除処理が必ず呼ばれるようにしておく
//            WakefulBroadcastReceiver.completeWakefulIntent(intent!!)
//        }
//    }
//}


//class ToastAlarmService : IntentService {
//    private var mContext: Context? = null
//    private var mHandler: Handler? = null
//
//    override fun onCreate() {
//        val thr = Thread(null, mTask, "ToastAlarmServiceThread")
//        thr.start()
//        Log.v(TAG, "onCreate")
//    }
//
//    // アラーム用サービス
//    internal var mTask: Runnable = Runnable {
//        // アラームを受け取るActivityを指定
//        val alarmBroadcast = Intent()
//        // ここでActionをセットする(Manifestに書いたものと同じであれば何でもよい)
//        alarmBroadcast.action = "AlarmAction"
//        // レシーバーへ渡す
//        sendBroadcast(alarmBroadcast)
//        // 役目を終えたサービスを止める
//        this@ToastAlarmService.stopSelf()
//        Log.v(TAG, "サービス停止")
//    }
//
//    constructor(name: String) : super(name) {
//        mHandler = Handler()
//    }
//
//    // call from startService()
//    constructor() : super("ToastAlarmService") {
//        mHandler = Handler()
//    }
//
//    override fun onHandleIntent(intent: Intent?) {
//        mContext = applicationContext
//        mHandler?.post(Runnable { Toast.makeText(mContext, "Toast Message from IntentService", Toast.LENGTH_LONG).show() })
//    }
//
//    companion object {
//        private val TAG = ToastAlarmService::class.java.simpleName
//    }
//}