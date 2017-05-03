package com.isolity.toastalarm.view

import android.app.TimePickerDialog
import android.support.v4.app.FragmentManager

/**
 * Created by shohei52a on 2017/05/03.
 */
object TimePickerManager {
    var fragmentManager: FragmentManager? = null

    fun show(hour:Int, minute:Int, onTimeSetListener: TimePickerDialog.OnTimeSetListener) {

        val timePicker = TimePickerDialogFragment(hour, minute, onTimeSetListener)
        timePicker.show(fragmentManager, "timePicker")
    }

}