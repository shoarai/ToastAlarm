package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class DailyAlarm(val id: Int, var timeOfDay: TimeOfDay) {
    var isPowerOn: Boolean = false

    fun powerOn() {
        isPowerOn = true
    }

    fun powerOff() {
        isPowerOn = false
    }
}