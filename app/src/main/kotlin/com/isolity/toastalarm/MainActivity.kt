package com.isolity.toastalarm

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.view.TimePickerManager


/**
 * Created by shoarai on 2017/04/17.
 */

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val weeklyAlarmListView by lazy {
        findViewById(R.id.weekly_alarm_list_view) as ListView
    }
    private val addWeeklyAlarmButton by lazy {
        findViewById(R.id.add_weekly_alarm_button) as FloatingActionButton
    }
    private val debugToastButton by lazy {
        findViewById(R.id.debug_toast_button) as FloatingActionButton
    }
    private val listAdapter by lazy {
        WeeklyAlarmListAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // HACK: Context
        WeeklyAlarmStorage.context = applicationContext
        WeeklyAlarmManager.context = applicationContext
        TimePickerManager.fragmentManager = supportFragmentManager

        initWeeklyAlarmList()

        addWeeklyAlarmButton.setOnClickListener { onClickAddButton() }
        debugToastButton.setOnClickListener { onClickDebugToastButton() }

        // DEBUG
//        closeApplication()

        Log.v(TAG, "end onCreate")
    }

    private fun initWeeklyAlarmList() {
        Log.v(TAG, "start showWeeklyAlarmList")
        listAdapter.weeklyAlarms = WeeklyAlarmManager.weeklyAlarms
        weeklyAlarmListView.adapter = listAdapter

        listAdapter.onClickDeleteButton = {
            weeklyAlarmId -> onClickDeleteButton(weeklyAlarmId)}

        // HACK: Get event from child list view
//        weeklyAlarmListView.setOnItemClickListener { parent, view, pos, id ->
//            val alarm = listAdapter.getItem(pos)
//            when (view.id) {
//                R.id.delete_button -> {
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        Log.v(TAG, "end showWeeklyAlarmList")
    }

    private fun onClickAddButton() {
        Log.v(TAG, "start onClickAddButton")
        val alarms = WeeklyAlarmManager.weeklyAlarms
        val newAlarm = WeeklyAlarmCreator.createWeeklyAlarm(alarms.toTypedArray())
        WeeklyAlarmManager.addWeeklyAlarm(newAlarm)

        listAdapter.notifyAlarmSetChanged()
        Log.v(TAG, "end onClickAddButton")
    }

    private fun onClickDeleteButton(weeklyAlarmId: Int) {
        Log.v(TAG, "start onClickDeleteButton")
        WeeklyAlarmManager.remove(weeklyAlarmId)
        listAdapter.notifyAlarmSetChanged()
        Log.v(TAG, "end onClickDeleteButton")
    }

    private fun onClickDebugToastButton() {
        ToastService.showToast(applicationContext)
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }
}

