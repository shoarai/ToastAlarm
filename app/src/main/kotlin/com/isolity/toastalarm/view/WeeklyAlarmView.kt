package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import com.isolity.toastalarm.R
import com.isolity.toastalarm.WeeklyAlarmManager
import com.isolity.toastalarm.model.DailyAlarm
import com.isolity.toastalarm.model.TimeOfDay
import com.isolity.toastalarm.model.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

//    val timeAlarmListView: ListView by bindView(R.id.time_alarm_list_view)

    private val timeTextView: TextView by bindView(R.id.time_text_view)
    private val powerSwitch: Switch by bindView(R.id.power_switch)
    private val deleteButton: ImageButton by bindView(R.id.delete_button)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weekly_alarm, this)
    }

    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        // TODO: Support multiple time alarm
        setTimeAlarm(weeklyAlarm.dailyAlarms[0])

        showWeekCheckboxState(weeklyAlarm.weeks)

        getWeeks().forEach {
            var checkboxId = getWeekCheckboxId(it)
            var checkbox = findViewById(checkboxId) as CheckBox
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                var week = getWeek(buttonView.id)
                WeeklyAlarmManager.setWeek(weeklyAlarm.id, week, isChecked)
            }
        }
    }

    private fun setTimeAlarm(dailyAlarm: DailyAlarm) {
        timeTextView.text = dailyAlarm.timeOfDay.toString()
        powerSwitch.isChecked = dailyAlarm.isPowerOn

        timeTextView.setOnClickListener {
            var timeOfDay = dailyAlarm.timeOfDay
            TimePickerManager.show(timeOfDay.hourOfDay, timeOfDay.minute, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                var newTimeOfDay = TimeOfDay(hourOfDay, minute)

                WeeklyAlarmManager.setTimeOfDay(dailyAlarm.id, newTimeOfDay)
                timeTextView.text = newTimeOfDay.toString()
            })
        }

        powerSwitch.setOnCheckedChangeListener { _, isChecked ->
            WeeklyAlarmManager.setPower(dailyAlarm.id, isChecked)
        }

        deleteButton.setOnClickListener {
            WeeklyAlarmManager.remove(dailyAlarm.id)
        }
    }


//    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
//        var listAdapter = TimeAlarmListAdapter(context)
//        listAdapter.timeAlarms = weeklyAlarm.timeAlarms.toList()
//        timeAlarmListView.adapter = listAdapter
//
//        timeAlarmListView.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(context, "timeAlarmListView:$position", Toast.LENGTH_SHORT).show()
//        }
//
//        showWeekCheckboxState(weeklyAlarm.weeks)
//
//        getWeeks().forEach {
//            var checkboxId = getWeekCheckboxId(it)
//            var checkbox = findViewById(checkboxId) as CheckBox
//            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
//                var week = getWeek(buttonView.id)
//                WeeklyAlarmManager.setWeek(weeklyAlarm.id, week, isChecked)
//            }
//        }
//    }

    private fun getWeeks(): Array<Int> {
        return arrayOf(
                Calendar.SUNDAY,
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
        )
    }

//    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
//        super.onWindowFocusChanged(hasWindowFocus)
//
//        if (hasWindowFocus) {
//            fitListViewHeightToItems()
//        }
//    }

//    private fun fitListViewHeightToItems() {
//        val listViewItem = timeAlarmListView.adapter.getView(0, null, timeAlarmListView)
//        listViewItem.measure(
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
//
//        val listViewItemHeight = listViewItem.measuredHeight
//        val listViewWrapperHeight = listViewItemHeight * timeAlarmListView.count
//
//        var params = timeAlarmListView.layoutParams
//        params.height = listViewWrapperHeight
//        timeAlarmListView.layoutParams = params
//    }

    private fun showWeekCheckboxState(weeks: Set<Int>) {
        weeks.forEach { week ->
            var id = getWeekCheckboxId(week)
            var weekCheckbox = findViewById(id) as CheckBox
            weekCheckbox.isChecked = true
        }
    }

    private fun getWeekCheckboxId(week: Int): Int {
        return when (week) {
            Calendar.SUNDAY -> R.id.week_sun_checkbox
            Calendar.MONDAY -> R.id.week_mon_checkbox
            Calendar.TUESDAY -> R.id.week_tue_checkbox
            Calendar.WEDNESDAY -> R.id.week_wed_checkbox
            Calendar.THURSDAY -> R.id.week_thu_checkbox
            Calendar.FRIDAY -> R.id.week_fri_checkbox
            Calendar.SATURDAY -> R.id.week_sat_checkbox
            else -> throw Error("Not found")
        }
    }

    private fun getWeek(id: Int): Int {
        return when (id) {
            R.id.week_sun_checkbox -> Calendar.SUNDAY
            R.id.week_mon_checkbox -> Calendar.MONDAY
            R.id.week_tue_checkbox -> Calendar.TUESDAY
            R.id.week_wed_checkbox -> Calendar.WEDNESDAY
            R.id.week_thu_checkbox -> Calendar.THURSDAY
            R.id.week_fri_checkbox -> Calendar.FRIDAY
            R.id.week_sat_checkbox -> Calendar.SATURDAY
            else -> throw Error("Not found")
        }
    }
}