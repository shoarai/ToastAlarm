package com.isolity.toastalarm

import android.content.Context
import android.widget.Toast
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*

/**
 * Created by shohei52a on 2017/08/16.
 */


object ToastService{
    fun showToast(context: Context){
        var now = getNowTime()
        Toast.makeText(context, "$now !!", Toast.LENGTH_LONG).show()
    }

    private fun getNowTime(): String {
        val now = Calendar.getInstance()
        val time = TimeOfDay(now.get(Calendar.HOUR), now.get(Calendar.MINUTE))
        return time.toString()
    }
}