package com.isolity.toastalarm.domain.entity

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created by shoarai on 2017/11/08.
 */
class WeeklyAlarmTest {
    private val time = TimeOfDay(10, 20)
    private val daily = DailyAlarm(1, time)
    private val alarm = WeeklyAlarm(0, daily)

    @Test
    fun addWeek() {
        val week = Calendar.MONDAY
        alarm.addWeek(week)
        Assert.assertEquals(week, alarm.weeks.single())
    }

    @Test
    fun removeWeek() {
        val week = Calendar.MONDAY
        alarm.addWeek(week)
        alarm.removeWeek(week)
        Assert.assertEquals(0, alarm.weeks.size)
    }
}