package com.isolity.toastalarm.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TimePicker

/**
 * Created by shoarai on 2017/04/22.
 */

class TimePickerDialogFragment(
        val hour: Int, val minute: Int,
        val onTimeSetListener: TimePickerDialog.OnTimeSetListener)
    : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timePickerDialog = TimePickerDialog(
                activity, this, hour, minute, true)
        return timePickerDialog
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        onTimeSetListener.onTimeSet(view, hourOfDay, minute)
    }
}