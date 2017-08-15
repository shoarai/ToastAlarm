package com.isolity.toastalarm.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import com.isolity.toastalarm.R
import com.isolity.toastalarm.model.WeeklyAlarm
import com.isolity.toastalarm.view.WeeklyAlarmView

/**
 * Created by shoarai on 2017/04/29.
 */

class WeeklyAlarmListAdapter(private val context: Context) : BaseAdapter() {

    var weeklyAlarms: MutableList<WeeklyAlarm> = mutableListOf()

    override fun getCount(): Int = weeklyAlarms.size

    override fun getItem(position: Int): Any? = weeklyAlarms[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = WeeklyAlarmView(context)
        view.setWeeklyAlarm(weeklyAlarms[position])

        val deleteButton = view?.findViewById(R.id.delete_button) as ImageButton
        deleteButton.setOnClickListener {
            val item = this.getItem(position) as WeeklyAlarm
            onClickDeleteButton?.invoke(item.id)
        }

        return view

//        return (convertView as? WeeklyAlarmView ?: WeeklyAlarmView(context)).apply {
//            setWeeklyAlarm(weeklyAlarms[position])
//        }
    }

    var onClickDeleteButton: ((weeklyAlarmId: Int) -> Unit)? = null

    fun update() {
        notifyDataSetChanged()
    }
}
