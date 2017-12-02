package com.isolity.toastalarm.application.view

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.isolity.toastalarm.R
import com.isolity.toastalarm.domain.entity.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/08/16.
 */

object ToastView {
    private val TAG = ToastView::class.java.simpleName

    /**
     * Show toast.
     * @param context context
     */
    fun showToast(context: Context) {
        Log.v(TAG, "start showToast")
        val now = getNowTime()
        val text = "$now"

//        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
//        return

        val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflate.inflate(R.layout.toast, null)
        val textView = view.findViewById(R.id.message) as TextView
        textView.text = text

        val adView = view.findViewById(R.id.adView) as AdView
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                showToast(context, view)
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                super.onAdFailedToLoad(errorCode)
                when (errorCode) {
                    AdRequest.ERROR_CODE_INTERNAL_ERROR ->
                        Log.v(TAG, "onAdFailedToLoad errorCode:ERROR_CODE_INTERNAL_ERROR")
                    AdRequest.ERROR_CODE_INVALID_REQUEST ->
                        Log.v(TAG, "onAdFailedToLoad errorCode:ERROR_CODE_INVALID_REQUEST")
                    AdRequest.ERROR_CODE_NETWORK_ERROR ->
                        Log.v(TAG, "onAdFailedToLoad errorCode:ERROR_CODE_NETWORK_ERROR")
                    AdRequest.ERROR_CODE_NO_FILL ->
                        Log.v(TAG, "onAdFailedToLoad errorCode:ERROR_CODE_NO_FILL")
                }
                showToast(context, view)
            }
        }
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        Log.v(TAG, "end showToast")
    }

    private fun showToast(context: Context, view: View) {
        Toast(context).run {
            this.view = view
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    private fun getNowTime(): String {
        val now = Calendar.getInstance()
        val time = TimeOfDay(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE))
        return time.toString()
    }
}