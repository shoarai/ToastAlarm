package com.isolity.toastalarm.application.library

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TimePicker

/**
 * Created by shoarai on 2017/04/22.
 */

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hourOfDay = arguments.getInt(HOUR_KEY)
        val minute = arguments.getInt(MINUTE_KEY)
        return TimePickerDialog(activity, this, hourOfDay, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        onTimeSet?.invoke(hourOfDay, minute)
    }

    var onTimeSet: ((hourOfDay: Int, minute: Int) -> Unit)? = null

    companion object {
        private const val HOUR_KEY = "hourOfDayKey"
        private const val MINUTE_KEY = "minuteKey"

        fun createInstance(hourOfDay: Int, minute: Int): TimePickerDialogFragment {
            return TimePickerDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(HOUR_KEY, hourOfDay)
                    putInt(MINUTE_KEY, minute)
                }
            }
        }
    }
}