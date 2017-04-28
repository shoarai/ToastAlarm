package com.isolity.toastalarm

import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.isolity.toastalarm.ToastAlarmSettingManager.timeAlarms

import com.isolity.toastalarm.model.TimeAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.view.TimePickerDialogFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        var listAdapter = TimeAlarmListAdapter(applicationContext)
        listAdapter.timeAlarms = listOf(
                ToastAlarmSettingManager.getFirst(),
                ToastAlarmSettingManager.getFirst())

        var listView:ListView = findViewById(R.id.time_alarm_list_view) as ListView
        listView.adapter = listAdapter


//        var timeAlarmView = TimeAlarmView(applicationContext)
//
//        var timeAlarm = ToastAlarmSettingManager.getFirst()
//        timeAlarmView.setTimeAlarm(timeAlarm)
//        setContentView(timeAlarmView)

        // DEBUG
//        closeApplication()
    }

    override fun onStart() {
        super.onStart()

        showAlarmSetting()
    }

    private fun initView() {
//        timeTextView.setOnClickListener {
//            showTimePickerDialog()
//        }

//        startAlarmSwitch.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                startAlarm()
//            } else {
//                stopAlarm();
//            }
//        }
    }

    private fun showAlarmSetting() {
        val alarmSettings = timeAlarms
        showAlarmSetting(alarmSettings)
    }

    private fun startAlarm() {
        ToastAlarmService.startAlarm(applicationContext)
//        ToastAlarmStarter(this).startAlarm()
        Toast.makeText(applicationContext, "Set Alarm", Toast.LENGTH_SHORT).show()
    }

    private fun stopAlarm() {
        ToastAlarmService.stopAlarm();
    }

    private fun showAlarmSetting(timeAlarms: Array<TimeAlarm>) {
        if (timeAlarms.size == 0) {
            return
        }
//        timeTextView.text = timeAlarms[0].timeOfDay.toString()
    }

    private fun showTimePickerDialog() {
        val alarmSetting = ToastAlarmSettingManager.getFirst()
        var time = alarmSetting.timeOfDay

        val timePicker = TimePickerDialogFragment(TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            //時刻が選択されたときの処理
            var time = TimeOfDay(hourOfDay, minute)
            var alarm = TimeAlarm(time)
            ToastAlarmSettingManager.set(alarm)

            showAlarmSetting(timeAlarms)
        })

        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun closeApplication() {
        finishAndRemoveTask()
//        finish()
//        moveTaskToBack(true);
    }

}
