package com.isolity.toastalarm.view

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker

/**
 * Created by shohei52a on 2017/04/22.
 */

import java.util.Calendar

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(activity, this, hour, minute, true)

        return timePickerDialog
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //時刻が選択されたときの処理
    }

}