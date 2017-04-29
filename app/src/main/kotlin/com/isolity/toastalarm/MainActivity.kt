package com.isolity.toastalarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm
import android.widget.Toast
import android.widget.AdapterView

/**
 * Created by shoarai on 2017/04/17.
 */

class MainActivity : AppCompatActivity() {

    val weeklyAlarmListView by lazy {
        findViewById(R.id.weekly_alarm_list_view) as ListView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms
        showWeeklyAlarmList(weeklyAlarms)

        // DEBUG
//        closeApplication()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun showWeeklyAlarmList(weeklyAlarms: Array<WeeklyAlarm>) {
        var listAdapter = WeeklyAlarmListAdapter(applicationContext)
        listAdapter.weeklyAlarms = weeklyAlarms.toList()
        weeklyAlarmListView.adapter = listAdapter

        weeklyAlarmListView.setOnItemClickListener { parent, view, pos, id ->
            // 選択アイテムを取得
            val listView = parent as ListView
            val item = listView.getItemAtPosition(pos) as String

            // 通知ダイアログを表示
            Toast.makeText(this@MainActivity,
                    item, Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
