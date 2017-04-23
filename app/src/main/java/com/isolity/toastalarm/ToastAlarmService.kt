package com.isolity.toastalarm

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.support.v4.content.WakefulBroadcastReceiver
import android.app.AlarmManager


/**
 * Created by shohei52a on 2017/04/22.
 */

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

class ToastAlarmService : IntentService("ToastAlarmService") {
    private val TAG = ToastAlarmService::class.java.simpleName

    private var mHandler: Handler = Handler()

    override fun onHandleIntent(intent: Intent) {
        var context = applicationContext
        mHandler.post(Runnable {
            Toast.makeText(context, "Toast Message from IntentService", Toast.LENGTH_LONG).show()
        })

//        Toast.makeText(applicationContext, "ToastAlarmService.Received", Toast.LENGTH_LONG).show()
        Log.d(TAG, "time:" + SystemClock.elapsedRealtime())
    }

    companion object {
        /**
         * サービスを処理する AlarmManager を開始する。
         * @param context
         */
        fun startAlarm(context: Context) {
            // 実行するサービスを指定する
            val pendingIntent = PendingIntent.getService(context, 0,
                    Intent(context, ToastAlarmService::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT)

            // 10秒毎にサービスの処理を実行する
            val am = context
                    .getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime(), (5 * 1000).toLong(), pendingIntent)
        }
    }
}

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