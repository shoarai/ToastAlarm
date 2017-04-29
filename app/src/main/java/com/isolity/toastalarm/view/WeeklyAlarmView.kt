package com.isolity.toastalarm.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ListView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.TimeAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weekly_alarm, this)
    }

    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        var listAdapter = TimeAlarmListAdapter(context)
        listAdapter.timeAlarms = weeklyAlarm.timeAlarms.toList()

        var listView = findViewById(R.id.time_alarm_list_view) as ListView
        listView.adapter = listAdapter


        showWeekCheckboxState(weeklyAlarm.weeks)
    }

    fun showWeekCheckboxState(weeks: Set<Int>) {
        weeks.forEach { week ->
            var id = getWeekCheckboxId(week)
            id?.apply {
                var weekCheckbox = findViewById(id) as CheckBox
                weekCheckbox.isChecked = true
            }
        }
    }

    private fun getWeekCheckboxId(week: Int): Int? {
        return when (week) {
            Calendar.SUNDAY -> R.id.week_sun_checkbox
            Calendar.MONDAY -> R.id.week_mon_checkbox
            Calendar.TUESDAY -> R.id.week_tue_checkbox
            Calendar.WEDNESDAY -> R.id.week_wed_checkbox
            Calendar.THURSDAY -> R.id.week_thu_checkbox
            Calendar.FRIDAY -> R.id.week_fri_checkbox
            Calendar.SATURDAY -> R.id.week_sat_checkbox
            else -> null
        }
    }
}