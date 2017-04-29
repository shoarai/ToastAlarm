package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class TimeAlarm(val timeOfDay: TimeOfDay) {
    var isPowerOn: Boolean = false
        get

    fun powerOn() {
        isPowerOn = true
    }

    fun powerOff() {
        isPowerOn = false
    }
}
