package com.isolity.toastalarm.model

import org.junit.Assert
import org.junit.Test

/**
 * Created by shohei52a on 2017/11/21.
 */
class WeeklyAlarmCreatorTest {
    @Test
    fun createWeeklyAlarm() {
        val alarm = WeeklyAlarmCreator.createWeeklyAlarm(emptyArray())
        Assert.assertEquals(1, alarm.dailyAlarms.size)
        Assert.assertTrue(alarm.dailyAlarms[0].isPowerOn)
    }

    @Test
    fun createWeeklyAlarmNotDuplicatedId() {
        val alarms = mutableListOf<WeeklyAlarm>()
        for (i in 1..100) {
            alarms.add(WeeklyAlarmCreator.createWeeklyAlarm(alarms.toTypedArray()))
        }
        Assert.assertEquals(100, alarms.map { it.id }.distinct().size)
        Assert.assertEquals(100, alarms.map { it.dailyAlarms[0].id }.distinct().size)
    }
}