package com.isolity.toastalarm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ListView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.TimeAlarmListAdapter
import com.isolity.toastalarm.model.WeeklyAlarm
import java.util.*

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmView : FrameLayout {
    constructor(context: Context?) : super(context)

    val timeAlarmListView: ListView by lazy {
        findViewById(R.id.time_alarm_list_view) as ListView
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weekly_alarm, this)
    }

    fun setWeeklyAlarm(weeklyAlarm: WeeklyAlarm) {
        var listAdapter = TimeAlarmListAdapter(context)
        listAdapter.timeAlarms = weeklyAlarm.timeAlarms.toList()
        timeAlarmListView.adapter = listAdapter

        showWeekCheckboxState(weeklyAlarm.weeks)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)

        if (hasWindowFocus) {
            fitListViewHeightToItems()
        }
    }

    fun fitListViewHeightToItems() {
        val listViewItem = timeAlarmListView.adapter.getView(0, null, timeAlarmListView)
        listViewItem.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))

        val listViewItemHeight = listViewItem.measuredHeight
        val listViewWrapperHeight = listViewItemHeight * timeAlarmListView.count

        var params = timeAlarmListView.layoutParams
        params.height = listViewWrapperHeight
        timeAlarmListView.layoutParams = params
    }

    fun showWeekCheckboxState(weeks: Set<Int>) {
        weeks.forEach { week ->
            var id = getWeekCheckboxId(week)
            if (id === null) return

            var weekCheckbox = findViewById(id) as CheckBox
            weekCheckbox.isChecked = true
        }
    }

    private fun getWeekCheckboxId(week: Int): Int? {
        return when (week) {
            Calendar.SUNDAY -> R.id.week_sun_checkbox
            Calendar.MONDAY -> R.id.week_mon_checkbox
            Calendar.TUESDAY -> R.id.week_tue_checkbox
            Calendar.WEDNESDAY -> R.id.week_wed_checkbox
            Calendar.THURSDAY -> R.id.week_thu_checkbox
            Calendar.FRIDAY -> R.id.week_fri_checkbox
            Calendar.SATURDAY -> R.id.week_sat_checkbox
            else -> null
        }
    }
}