package com.isolity.toastalarm.model

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created by shohei52a on 2017/11/08.
 */
class WeeklyAlarmUtilTest {
    @Test
    fun getNextAlarmCalendar() {
        val now = Calendar.getInstance()
        val nextMinute = 10
        var expected = (now.clone() as Calendar).apply {
            add(Calendar.MINUTE, nextMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val alarms = arrayListOf(now.let {
            val cal = (now.clone() as Calendar).apply {
                add(Calendar.DATE, -1)
                add(Calendar.MINUTE, nextMinute / 2)
            }
            WeeklyAlarm(2,
                    DailyAlarm(2, TimeOfDay(
                            cal.get(Calendar.HOUR_OF_DAY),
                            cal.get(Calendar.MINUTE))
                    ).apply { powerOn() }
            ).apply {
                addWeek(cal.get(Calendar.DAY_OF_WEEK))
            }
        }, now.let {
            val cal = (now.clone() as Calendar).apply {
                add(Calendar.MINUTE, -nextMinute)
            }
            WeeklyAlarm(2,
                    DailyAlarm(2, TimeOfDay(
                            cal.get(Calendar.HOUR_OF_DAY),
                            cal.get(Calendar.MINUTE))
                    ).apply { powerOn() }
            ).apply {
                addWeek(cal.get(Calendar.DAY_OF_WEEK))
            }
        }, now.let {
            val cal = (now.clone() as Calendar).apply {
                add(Calendar.MINUTE, nextMinute / 2)
            }
            WeeklyAlarm(2,
                    DailyAlarm(2, TimeOfDay(
                            cal.get(Calendar.HOUR_OF_DAY),
                            cal.get(Calendar.MINUTE))
                    ).apply { powerOff() }
            ).apply {
                addWeek(cal.get(Calendar.DAY_OF_WEEK))
            }
        }, now.let {
            WeeklyAlarm(1,
                    DailyAlarm(1, TimeOfDay(
                            expected.get(Calendar.HOUR_OF_DAY),
                            expected.get(Calendar.MINUTE))
                    ).apply { powerOn() }
            ).apply {
                addWeek(expected.get(Calendar.DAY_OF_WEEK))
            }
        }, now.let {
            val cal = (now.clone() as Calendar).apply {
                add(Calendar.MINUTE, nextMinute * 2)
            }
            WeeklyAlarm(2,
                    DailyAlarm(2, TimeOfDay(
                            cal.get(Calendar.HOUR_OF_DAY),
                            cal.get(Calendar.MINUTE))
                    ).apply { powerOn() }
            ).apply {
                addWeek(cal.get(Calendar.DAY_OF_WEEK))
            }
        })

        val actual = WeeklyAlarmUtil.getNextAlarmCalendar(alarms)

        Assert.assertNotNull(actual)
        Assert.assertEquals(expected.timeInMillis, actual?.timeInMillis)
    }

    @Test
    fun getNextAlarmCalendarInNextWeek() {
        val now = Calendar.getInstance()
        val beforeMinute = 10
        var expected = (now.clone() as Calendar).apply {
            add(Calendar.DATE, 6)
            add(Calendar.MINUTE, -beforeMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val alarms = arrayListOf(now.let {
            WeeklyAlarm(1,
                    DailyAlarm(1, TimeOfDay(
                            expected.get(Calendar.HOUR_OF_DAY),
                            expected.get(Calendar.MINUTE))
                    ).apply { powerOn() }
            ).apply {
                addWeek(expected.get(Calendar.DAY_OF_WEEK))
            }
        })

        val actual = WeeklyAlarmUtil.getNextAlarmCalendar(alarms)

        Assert.assertNotNull(actual)
        Assert.assertEquals(expected.timeInMillis, actual?.timeInMillis)
    }

    @Test
    fun getNextAlarmAsCalendarWhenAllPowerOff() {
        val now = Calendar.getInstance()
        val nowHour = now.get(Calendar.HOUR_OF_DAY)
        val nowMinute = now.get(Calendar.MINUTE)
        val nowWeek = now.get(Calendar.DAY_OF_WEEK)

        val alarms = arrayListOf(
                WeeklyAlarm(1,
                        DailyAlarm(1, TimeOfDay(nowHour, nowMinute))
                                .apply { powerOff() }
                ).apply {
                    addWeek(nowWeek)
                }
        )

        val actual = WeeklyAlarmUtil.getNextAlarmCalendar(alarms)

        Assert.assertNull(actual)
    }
}