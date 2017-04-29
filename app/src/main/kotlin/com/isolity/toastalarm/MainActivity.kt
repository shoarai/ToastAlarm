package com.isolity.toastalarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm

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
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
