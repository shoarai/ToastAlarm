package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import org.junit.Assert
import org.junit.Test

/**
 * Created by shoarai on 2017/11/21.
 */
class WeeklyAlarmCreatorTest {
    @Test
    fun createWeeklyAlarm() {
        val timeOfDay = TimeOfDay(10, 10)
        val alarm = WeeklyAlarmCreator.create(emptyArray(), timeOfDay)

        Assert.assertEquals(1, alarm.dailyAlarms.size)
        Assert.assertEquals(timeOfDay, alarm.dailyAlarms[0].timeOfDay)
        Assert.assertTrue(alarm.dailyAlarms[0].isPowerOn)
    }

    @Test
    fun createWeeklyAlarmNotDuplicatedId() {
        val alarms = mutableListOf<WeeklyAlarm>()
        val num = 100
        for (i in 1..num) {
            alarms.add(WeeklyAlarmCreator.create(
                    alarms.toTypedArray(), TimeOfDay(10, 10)))
        }
        Assert.assertEquals(num, alarms.map { it.id }.distinct().size)
        Assert.assertEquals(num, alarms.map { it.dailyAlarms.map { it.id } }.distinct().size)
    }
}