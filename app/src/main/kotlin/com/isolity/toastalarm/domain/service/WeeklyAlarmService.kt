package com.isolity.toastalarm.domain.service

import com.isolity.toastalarm.domain.`interface`.IWeeklyAlarmRepository
import com.isolity.toastalarm.domain.`interface`.IWeeklyAlarmService
import com.isolity.toastalarm.domain.entity.WeeklyAlarm

/**
 * Created by shoarai on 2017/04/22.
 */

class WeeklyAlarmService(repository: IWeeklyAlarmRepository)
    : CommonService<WeeklyAlarm>(repository), IWeeklyAlarmService