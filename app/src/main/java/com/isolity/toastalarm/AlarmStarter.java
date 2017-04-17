package com.isolity.toastalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by shohei52a on 2017/04/17.
 */

public class AlarmStarter {
    Context context;

    AlarmStarter(Context context) {
        this.context = context;
    }

    public void startAlarm() {
        // 時間をセットする
        Calendar calendar = Calendar.getInstance();
        // Calendarを使って現在の時間をミリ秒で取得
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 5秒後に設定
        calendar.add(Calendar.SECOND, 10);

        Intent intent = new Intent(context.getApplicationContext(), AlarmBroadcastReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 0);

        // アラームをセットする
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

        Toast.makeText(context.getApplicationContext(), "Set Alarm ", Toast.LENGTH_SHORT).show();
    }
}
