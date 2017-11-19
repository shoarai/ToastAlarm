package com.isolity.toastalarm.model

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created by shohei52a on 2017/11/08.
 */
class WeeklyAlarmUtilTest {
//    private val time = TimeOfDay(10, 20)
//    private val daily = DailyAlarm(1, time)
//    private val weeklyAlarm = WeeklyAlarm(0, daily)
//
//    @Before
//    fun setUp() {
//        val weeklyAlarms = arrayOf(weeklyAlarm)
//        alarmList = WeeklyAlarmList(weeklyAlarms)
//    }

    @Test
    fun hasPowerOnIsTrue() {
        val time = TimeOfDay(10, 20)
        val daily = DailyAlarm(1, time)
        daily.powerOn()
        val weeklyAlarm = WeeklyAlarm(1, daily)
        weeklyAlarm.addWeek(Calendar.MONDAY)
        val weeklyAlarms = listOf(weeklyAlarm)

        Assert.assertEquals(true, WeeklyAlarmUtil.hasPowerOn(weeklyAlarms))
    }

    @Test
    fun hasPowerOnIsFalse() {
        val time = TimeOfDay(10, 20)
        val daily = DailyAlarm(1, time)
        val weeklyAlarm = WeeklyAlarm(1, daily)
        weeklyAlarm.addWeek(Calendar.MONDAY)
        val weeklyAlarms = listOf(weeklyAlarm)

        Assert.assertEquals(true, WeeklyAlarmUtil.hasPowerOn(weeklyAlarms))
    }

//    @Test
//    fun getNextAlarmAsCalendar() {
//        val nextMinute = 10
//        val alarms = mutableListOf<WeeklyAlarm>()
//        val now = Calendar.getInstance()
//
//        val time = TimeOfDay(now.get(Calendar.HOUR), now.get(Calendar.MINUTE) + nextMinute)
//        val daily = DailyAlarm(10, time)
//        daily.powerOn()
//        val alarm = WeeklyAlarm(1, daily)
//        alarm.addWeek(now.get(Calendar.DAY_OF_WEEK))
//        alarms.add(alarm)
//
//        val time2 = TimeOfDay(now.get(Calendar.HOUR), now.get(Calendar.MINUTE) - 10)
//        val daily2 = DailyAlarm(10, time2)
//        daily2.powerOn()
//        val alarm2 = WeeklyAlarm(1, daily2)
//        alarm.addWeek(now.get(Calendar.DAY_OF_WEEK))
//        alarms.add(alarm2)
//
//        val time3 = TimeOfDay(now.get(Calendar.HOUR), now.get(Calendar.MINUTE) + nextMinute)
//        val daily3 = DailyAlarm(10, time3)
//        daily3.powerOn()
//        val alarm3 = WeeklyAlarm(1, daily3)
//        val yesterday = now
//        yesterday.add(Calendar.DAY_OF_WEEK, -1)
//        alarm.addWeek(yesterday.get(Calendar.DAY_OF_WEEK))
//        alarms.add(alarm3)
//
//        val timeOff = TimeOfDay(now.get(Calendar.HOUR), now.get(Calendar.MINUTE) + 5)
//        val dailyOff = DailyAlarm(10, timeOff)
//        val alarmOff = WeeklyAlarm(1, dailyOff)
//        alarmOff.addWeek(now.get(Calendar.DAY_OF_WEEK))
//        alarms.add(alarmOff)
//
//        val actual = WeeklyAlarmUtil.getNextAlarmAsCalendar(alarms)
//
//        Assert.assertEquals(now.get(Calendar.DAY_OF_WEEK), actual.get(Calendar.DAY_OF_WEEK))
//        Assert.assertEquals(now.get(Calendar.HOUR), actual.get(Calendar.HOUR))
//        Assert.assertEquals(now.get(Calendar.MINUTE) + nextMinute, actual.get(Calendar.MINUTE))
//    }
}