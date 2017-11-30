package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.support.v4.app.FragmentManager
import com.isolity.toastalarm.model.TimeOfDay

/**
 * Created by shohei52a on 2017/05/03.
 */
object TimePickerManager {
    var fragmentManager: FragmentManager? = null

    fun show(hourOfDay: Int, minute: Int, onTimeSet: (timeOfDay: TimeOfDay) -> Unit) {
        val timePicker = TimePickerDialogFragment(
                hourOfDay, minute, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            onTimeSet(TimeOfDay(hourOfDay, minute))
        })
        timePicker.show(fragmentManager, "timePicker")
    }

    fun show(timeOfDay: TimeOfDay, onTimeSet: (timeOfDay: TimeOfDay) -> Unit) {
        show(timeOfDay.hourOfDay, timeOfDay.minute, onTimeSet)
    }
}