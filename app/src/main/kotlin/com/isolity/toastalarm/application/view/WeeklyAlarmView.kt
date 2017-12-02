package com.isolity.toastalarm.application.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import com.isolity.toastalarm.R
import com.isolity.toastalarm.application.extension.bindView
import com.isolity.toastalarm.domain.entity.DailyAlarm
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmView(context: Context) : FrameLayout(context) {
    private val timeTextView: TextView by bindView(R.id.time_text_view)
    private val powerSwitch: Switch by bindView(R.id.power_switch)
    private val deleteButton: ImageButton by bindView(R.id.delete_button)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weekly_alarm, this)
    }

    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        setTimeAlarm(weeklyAlarm, weeklyAlarm.dailyAlarms[0])
        setWeek(weeklyAlarm)
    }

    var onUpdate: ((weeklyAlarm: WeeklyAlarm) -> Unit)? = null
    var onDelete: ((weeklyAlarm: WeeklyAlarm) -> Unit)? = null

    private fun setTimeAlarm(weeklyAlarm: WeeklyAlarm, dailyAlarm: DailyAlarm) {
        timeTextView.text = dailyAlarm.timeOfDay.toString()
        powerSwitch.isChecked = dailyAlarm.isPowerOn

        timeTextView.setOnClickListener {
            var timeOfDay = dailyAlarm.timeOfDay
            TimeOfDayPickerDialog.show(timeOfDay, { newTimeOfDay ->
                weeklyAlarm.dailyAlarms[0].timeOfDay = newTimeOfDay
                timeTextView.text = newTimeOfDay.toString()
                onUpdate?.invoke(weeklyAlarm)
            })
        }

        powerSwitch.setOnCheckedChangeListener { _, isChecked ->
            weeklyAlarm.dailyAlarms[0].isPowerOn = isChecked
            onUpdate?.invoke(weeklyAlarm)
        }

        deleteButton.setOnClickListener {
            onDelete?.invoke(weeklyAlarm)
        }
    }

    private fun setWeek(weeklyAlarm: WeeklyAlarm) {
        weeklyAlarm.weeks.forEach { week ->
            var id = weekToWeekCheckboxId(week)
            var weekCheckbox = findViewById(id) as CheckBox
            weekCheckbox.isChecked = true
        }

        getAllWeeks().forEach {
            var checkboxId = weekToWeekCheckboxId(it)
            var checkbox = findViewById(checkboxId) as CheckBox
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                var week = weekCheckboxIdToWeek(buttonView.id)
                if (isChecked) {
                    weeklyAlarm.addWeek(week)
                } else {
                    weeklyAlarm.removeWeek(week)
                }
                onUpdate?.invoke(weeklyAlarm)
            }
        }
    }

    private fun getAllWeeks(): Array<Int> {
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

    private fun weekToWeekCheckboxId(week: Int): Int {
        return when (week) {
            Calendar.SUNDAY -> R.id.week_sun_checkbox
            Calendar.MONDAY -> R.id.week_mon_checkbox
            Calendar.TUESDAY -> R.id.week_tue_checkbox
            Calendar.WEDNESDAY -> R.id.week_wed_checkbox
            Calendar.THURSDAY -> R.id.week_thu_checkbox
            Calendar.FRIDAY -> R.id.week_fri_checkbox
            Calendar.SATURDAY -> R.id.week_sat_checkbox
            else -> throw IllegalArgumentException("Not supported week")
        }
    }

    private fun weekCheckboxIdToWeek(checkboxId: Int): Int {
        return when (checkboxId) {
            R.id.week_sun_checkbox -> Calendar.SUNDAY
            R.id.week_mon_checkbox -> Calendar.MONDAY
            R.id.week_tue_checkbox -> Calendar.TUESDAY
            R.id.week_wed_checkbox -> Calendar.WEDNESDAY
            R.id.week_thu_checkbox -> Calendar.THURSDAY
            R.id.week_fri_checkbox -> Calendar.FRIDAY
            R.id.week_sat_checkbox -> Calendar.SATURDAY
            else -> throw IllegalArgumentException("Not supported week button id")
        }
    }
}