package com.isolity.toastalarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.isolity.toastalarm.adapter.WeeklyAlarmListAdapter
import com.isolity.toastalarm.alarm.OnceAlarmManager
import com.isolity.toastalarm.alarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarmCreator
import com.isolity.toastalarm.view.TimePickerManager
import com.isolity.toastalarm.view.ToastView
import java.util.*

/**
 * Created by shoarai on 2017/04/17.
 */

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val weeklyAlarmListView by lazy {
        findViewById(R.id.weekly_alarm_list_view) as ListView
    }
    private val addWeeklyAlarmButton by lazy {
        findViewById(R.id.add_weekly_alarm_button) as FloatingActionButton
    }
    private val debugToastButton by lazy {
        findViewById(R.id.debug_toast_button) as FloatingActionButton
    }
    private val debugStartAlarmButton by lazy {
        findViewById(R.id.debug_start_alarm_button) as FloatingActionButton
    }
    private val listAdapter by lazy {
        WeeklyAlarmListAdapter(applicationContext, weeklyAlarmDataManager)
    }
    private val weeklyAlarmDataManager by lazy {
        WeeklyAlarmDataManager(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "start onCreate")

        setContentView(R.layout.activity_main)

        // HACK: Context
        TimePickerManager.fragmentManager = supportFragmentManager

        setWeeklyAlarmListView()

        val weeklyAlarms = weeklyAlarmDataManager.getAll()
        WeeklyAlarmManager.startNextAlarm(applicationContext, weeklyAlarms)

        setListener()

        showAdView()

        Log.v(TAG, "end onCreate")
    }

    private fun setListener() {
        addWeeklyAlarmButton.setOnClickListener { onClickAddButton() }
        debugToastButton.setOnClickListener { onClickDebugToastButton() }
        debugStartAlarmButton.setOnClickListener { onClickDebugStartAlarmButton() }
    }

    private fun showAdView() {
        val mAdView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun setWeeklyAlarmListView() {
        Log.v(TAG, "start showWeeklyAlarmList")

        listAdapter.onChangeAlarms = { weeklyAlarms ->
            WeeklyAlarmManager.startNextAlarm(applicationContext, weeklyAlarms)
        }
        weeklyAlarmListView.adapter = listAdapter

        Log.v(TAG, "end showWeeklyAlarmList")
    }

    private fun onClickAddButton() {
        Log.v(TAG, "start onClickAddButton")

        val now = Calendar.getInstance()
        val hourOfDay = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)

        TimePickerManager.show(hourOfDay, minute, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            addWeeklyAlarm(TimeOfDay(hourOfDay, minute))
        })

        Log.v(TAG, "end onClickAddButton")
    }

    private fun addWeeklyAlarm(timeOfDay: TimeOfDay) {
        val weeklyAlarms = weeklyAlarmDataManager.getAll()
        val newAlarm = WeeklyAlarmCreator.createWeeklyAlarm(weeklyAlarms.toTypedArray())
        newAlarm.dailyAlarms.first().timeOfDay = timeOfDay
        listAdapter.add(newAlarm)
    }

    private fun onClickDebugStartAlarmButton() {
        startDebugAlarm()
    }

    private fun startDebugAlarm() {
        val cal = Calendar.getInstance()
        cal.add(Calendar.SECOND, 10)
        OnceAlarmManager.startAlarm(applicationContext, cal)
    }

    private fun onClickDebugToastButton() {
        ToastView.showToast(applicationContext)
    }
}