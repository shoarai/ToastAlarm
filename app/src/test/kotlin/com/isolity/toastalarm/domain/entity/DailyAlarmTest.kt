package com.isolity.toastalarm.domain.entity

import org.junit.Assert
import org.junit.Test

/**
 * Created by shoarai on 2017/12/01.
 */
class DailyAlarmTest {
    @Test
    fun powerOn() {
        val alarm = DailyAlarm(0, TimeOfDay(10, 10))
        alarm.isPowerOn = false
        alarm.powerOn()
        Assert.assertTrue(alarm.isPowerOn)
    }

    @Test
    fun powerOff() {
        val alarm = DailyAlarm(0, TimeOfDay(10, 10))
        alarm.isPowerOn = true
        alarm.powerOff()
        Assert.assertFalse(alarm.isPowerOn)
    }
}