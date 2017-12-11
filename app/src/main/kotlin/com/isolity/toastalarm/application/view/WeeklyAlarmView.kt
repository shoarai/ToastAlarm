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
    private val weekByCheckboxId = hashMapOf(
            R.id.week_sun_checkbox to Calendar.SUNDAY,
            R.id.week_mon_checkbox to Calendar.MONDAY,
            R.id.week_tue_checkbox to Calendar.TUESDAY,
            R.id.week_wed_checkbox to Calendar.WEDNESDAY,
            R.id.week_thu_checkbox to Calendar.THURSDAY,
            R.id.week_fri_checkbox to Calendar.FRIDAY,
            R.id.week_sat_checkbox to Calendar.SATURDAY
    )

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
            TimeOfDayPickerDialog.show(dailyAlarm.timeOfDay, { newTimeOfDay ->
                dailyAlarm.timeOfDay = newTimeOfDay
                timeTextView.text = newTimeOfDay.toString()
                onUpdate?.invoke(weeklyAlarm)
            })
        }

        powerSwitch.setOnCheckedChangeListener { _, isChecked ->
            dailyAlarm.isPowerOn = isChecked
            onUpdate?.invoke(weeklyAlarm)
        }

        deleteButton.setOnClickListener {
            onDelete?.invoke(weeklyAlarm)
        }
    }

    private fun setWeek(weeklyAlarm: WeeklyAlarm) {
        weekByCheckboxId.forEach {
            val checkbox = findViewById(it.key) as CheckBox

            if (weeklyAlarm.weeks.contains(it.value)) {
                checkbox.isChecked = true
            }

            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                weekByCheckboxId[buttonView.id]?.run {
                    if (isChecked) {
                        weeklyAlarm.addWeek(it.value)
                    } else {
                        weeklyAlarm.removeWeek(it.value)
                    }
                    onUpdate?.invoke(weeklyAlarm)
                }
            }
        }
    }
}