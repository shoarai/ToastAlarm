package com.isolity.toastalarm

import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.*
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm
import android.widget.Toast
import com.isolity.toastalarm.view.TimePickerManager

/**
 * Created by shoarai on 2017/04/17.
 */

class MainActivity : AppCompatActivity() {

    val weeklyAlarmListView by lazy {
        findViewById(R.id.weekly_alarm_list_view) as ListView
    }

    val addWeeklyAlarmButton by lazy {
        findViewById(R.id.add_weekly_alarm_button) as FloatingActionButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeeklyAlarmStorage.context = applicationContext
        WeeklyAlarmManager.context = applicationContext

        var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms.toTypedArray()
        showWeeklyAlarmList(weeklyAlarms)


        TimePickerManager.fragmentManager = supportFragmentManager


        addWeeklyAlarmButton.setOnClickListener {
//            var weeklyAlarm = WeeklyAlarmManager.addWeeklyAlarm()
//            var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms.toTypedArray()
//            showWeeklyAlarmList(weeklyAlarms)

            Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
        }


        // DEBUG
//        closeApplication()
    }

    private fun showWeeklyAlarmList(weeklyAlarms: Array<WeeklyAlarm>) {
        var listAdapter = WeeklyAlarmListAdapter(applicationContext)
        listAdapter.weeklyAlarms = weeklyAlarms.toList()
        weeklyAlarmListView.adapter = listAdapter

//        weeklyAlarmListView.setOnItemClickListener { parent, view, pos, id ->
//            var viewId = view.getId()
//            Toast.makeText(applicationContext, "weeklyAlarm: $viewId", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
