package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class TimeOfDay(val hourOfDay: Int, val minute: Int) {
    override fun toString(): String =
            String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)
}