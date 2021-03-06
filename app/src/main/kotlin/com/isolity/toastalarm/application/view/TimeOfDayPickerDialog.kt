package com.isolity.toastalarm.application.view

import android.support.v4.app.FragmentManager
import com.isolity.toastalarm.application.library.TimePickerDialogFragment
import com.isolity.toastalarm.domain.entity.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/05/03.
 */
object TimeOfDayPickerDialog {
    var fragmentManager: FragmentManager? = null

    fun show(timeOfDay: TimeOfDay, onTimeSet: (timeOfDay: TimeOfDay) -> Unit) {
        show(timeOfDay.hourOfDay, timeOfDay.minute, onTimeSet)
    }

    fun showCurrentTime(onTimeSet: (timeOfDay: TimeOfDay) -> Unit) {
        val now = Calendar.getInstance()
        val hourOfDay = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)
        show(hourOfDay, minute, onTimeSet)
    }

    private fun show(hourOfDay: Int, minute: Int, onTimeSet: (timeOfDay: TimeOfDay) -> Unit) {
        val timePicker = TimePickerDialogFragment.createInstance(hourOfDay, minute)
        timePicker.onTimeSet = { hourOfDay, minute ->
            onTimeSet(TimeOfDay(hourOfDay, minute))
        }
        timePicker.show(fragmentManager, "timePicker")
    }
}