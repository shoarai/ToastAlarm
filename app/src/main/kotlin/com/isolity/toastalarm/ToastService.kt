package com.isolity.toastalarm

import android.content.Context
import android.os.Debug
import android.view.LayoutInflater
import android.widget.Toast
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*
import android.view.Gravity
import android.widget.TextView

/**
 * Created by shoarai on 2017/08/16.
 */

object ToastService{
    /**
     * Show toast.
     * @param context context
     */
    fun showToast(context: Context){
        val now = getNowTime()
        val text = "$now"

//        Toast.makeText(context,text, Toast.LENGTH_LONG).show()

        val toast = Toast(context)
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflate.inflate(R.layout.toast, null)
//        LayoutInflater.from(context).inflate(R.layout.toast, null)

        val textView = view.findViewById(R.id.message) as TextView
        textView.text = text

        toast.view = view
        toast.run {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.BOTTOM, 0, 300)
            show()
        }
    }

    private fun getNowTime(): String {
        val now = Calendar.getInstance()
        val time = TimeOfDay(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE))
        return time.toString()
    }

    fun showDebugToast(context : Context, text: String){
        return

        val toast = Toast(context)
        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflate.inflate(R.layout.toast, null)
        val textView = view.findViewById(R.id.message) as TextView
        textView.text = text
        toast.view = view
        toast.run {
            duration = Toast.LENGTH_SHORT
            setGravity(Gravity.BOTTOM, 0, 300)
            show()
        }
    }
}