package com.isolity.toastalarm.application.`interface`

import com.isolity.toastalarm.domain.entity.TimeOfDay
import com.isolity.toastalarm.domain.entity.WeeklyAlarm

/**
 * Created by shoarai on 2017/12/02.
 */
interface IWeeklyAlarmAppService : IAppService<WeeklyAlarm> {
    fun create(timeOfDay: TimeOfDay)
}