package com.isolity.toastalarm.model

/**
 * Created by shohei52a on 2017/04/22.
 */

class TimeOfDay(private val hour: Int, private val minute: Int) {

    override fun toString(): String {
        return String.format("%02d", hour) + ":" + String.format("%02d", minute)
    }
}
