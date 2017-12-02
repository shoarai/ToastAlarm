package com.isolity.toastalarm.application.service

import com.isolity.toastalarm.application.`interface`.IWeeklyAlarmAppService
import com.isolity.toastalarm.domain.`interface`.IWeeklyAlarmService
import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm
import com.isolity.toastalarm.domain.service.WeeklyAlarmCreator
import java.util.*

/**
 * Created by shoarai on 2017/12/02.
 */

class WeeklyAlarmAppService(private val service: IWeeklyAlarmService)
    : CommonAppService<WeeklyAlarm>(service), IWeeklyAlarmAppService {
    override fun create(timeOfDay: TimeOfDay) {
        val weeklyAlarms = getAll()
        val newAlarm = WeeklyAlarmCreator.create(weeklyAlarms.toTypedArray(), timeOfDay)
        newAlarm.addWeek(
                Calendar.SUNDAY,
                Calendar.MONDAY,
                Calendar.TUESDAY,
                Calendar.WEDNESDAY,
                Calendar.THURSDAY,
                Calendar.FRIDAY,
                Calendar.SATURDAY
        )
        service.add(newAlarm)
    }
}