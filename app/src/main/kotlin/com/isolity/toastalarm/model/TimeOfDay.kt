package com.isolity.toastalarm.model

/**
 * Created by shoarai on 2017/04/22.
 */

class TimeOfDay( val hour: Int, val minute: Int) {

    override fun toString(): String =
            String.format("%02d", hour) + ":" +
            String.format("%02d", minute)
}
