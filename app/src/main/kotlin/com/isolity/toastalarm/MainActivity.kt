package com.isolity.toastalarm

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.view.TimePickerManager

/**
 * Created by shoarai on 2017/04/17.
 */

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    val weeklyAlarmListView by lazy {
        findViewById(R.id.weekly_alarm_list_view) as ListView
    }

    val addWeeklyAlarmButton by lazy {
        findViewById(R.id.add_weekly_alarm_button) as FloatingActionButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // HACK: Context
        WeeklyAlarmStorage.context = applicationContext
        WeeklyAlarmManager.context = applicationContext


        TimePickerManager.fragmentManager = supportFragmentManager

        showWeeklyAlarmList()


        addWeeklyAlarmButton.setOnClickListener { onClickAddButton() }


        WeeklyAlarmManager.update = {
            showWeeklyAlarmList()
//            weeklyAlarmListView.refreshDrawableState()
        }

        // DEBUG
//        closeApplication()

        Log.v(TAG, "end onCreate")
    }

    private fun onClickAddButton() {
        Log.v(TAG, "start onClickAddButton")
        var weeklyAlarm = WeeklyAlarmCreator.createWeeklyAlarm()
        WeeklyAlarmManager.addWeeklyAlarm(weeklyAlarm)
//        showWeeklyAlarmList()

        Toast.makeText(applicationContext, "Add", Toast.LENGTH_SHORT).show()
        Log.v(TAG, "end onClickAddButton")
    }

    private fun showWeeklyAlarmList() {
        Log.v(TAG, "start showWeeklyAlarmList")
        var weeklyAlarms = WeeklyAlarmManager.weeklyAlarms.toTypedArray()

        var listAdapter = WeeklyAlarmListAdapter(applicationContext)
        listAdapter.weeklyAlarms = weeklyAlarms.toList()
        weeklyAlarmListView.adapter = listAdapter

//        weeklyAlarmListView.setOnItemClickListener { parent, view, pos, id ->
//            var viewId = view.getId()
//            Toast.makeText(applicationContext, "weeklyAlarm: $viewId", Toast.LENGTH_SHORT).show()
//        }
        Log.v(TAG, "end showWeeklyAlarmList")
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
