package com.isolity.toastalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by shohei52a on 2017/04/17.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // toast で受け取りを確認
        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();
    }

//    private void showToast() { b
//        Context context = getApplicationContext();
//        CharSequence text = "Hello toast!";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }
}