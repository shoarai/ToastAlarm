package com.isolity.toastalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.os.Handler
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log
import android.app.IntentService



/**
 * Created by shohei52a on 2017/04/17.
 */

//class ToastAlarmBroadcastReceiver : BroadcastReceiver() {
//
//    private val TAG = ToastAlarmBroadcastReceiver::class.java.simpleName
//
//    override fun onReceive(context: Context, intent: Intent) {
//        Log.v(TAG, "onReceive")
//
//
//        // toast で受け取りを確認
//        Toast.makeText(context, "Received111", Toast.LENGTH_LONG).show()
//    }
//
//    //    private void showToast() { b
//    //        Context context = getApplicationContext();
//    //        CharSequence text = "Hello toast!";
//    //        int duration = Toast.LENGTH_SHORT;
//    //
//    //        Toast toast = Toast.makeText(context, text, duration);
//    //        toast.show();
//    //    }
//}


class ToastAlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 他のアプリ更新時は対象外とする
        if (intent.action == Intent.ACTION_PACKAGE_REPLACED) {
            if (intent.dataString != "package:" + context.packageName) {
                return
            }
        }

        // AlarmManager を開始する
        ToastAlarmService.startAlarm(context)
    }
}

//
//class ToastAlarmBroadcastReceiver : WakefulBroadcastReceiver() {
//
//    private val TAG = ToastAlarmBroadcastReceiver::class.java.simpleName
//
//    override fun onReceive(context: Context, intent: Intent) {
//        Log.v(TAG, "onReceive111")
////        val serviceIntent = Intent(context, ToastAlarmService::class.java)
////        // サービス起動
////        WakefulBroadcastReceiver.startWakefulService(context, serviceIntent)
//
//        // toast で受け取りを確認
//        Toast.makeText(context, "ToastAlarmBroadcastReceiver.Received", Toast.LENGTH_LONG).show()
//    }
//
//    //    private void showToast() { b
//    //        Context context = getApplicationContext();
//    //        CharSequence text = "Hello toast!";
//    //        int duration = Toast.LENGTH_SHORT;
//    //
//    //        Toast toast = Toast.makeText(context, text, duration);
//    //        toast.show();
//    //    }
//}


//class MyService : IntentService("MyService") {
//
//    override fun onHandleIntent(intent: Intent?) {
//        try {
//            // サービス内部の処理
//        } finally {
//            // Wakelockの解除処理が必ず呼ばれるようにしておく
//            WakefulBroadcastReceiver.completeWakefulIntent(intent!!)
//        }
//    }
//}
