package com.isolity.toastalarm.domain.entity

import org.junit.Assert
import org.junit.Test

/**
 * Created by shoarai on 2017/11/08.
 */
class TimeOfDayTest {
    @Test
    fun toStringTest() {
        Assert.assertEquals(
                "10:20", TimeOfDay(10, 20).toString())
        Assert.assertEquals(
                "01:02", TimeOfDay(1, 2).toString())
    }
}