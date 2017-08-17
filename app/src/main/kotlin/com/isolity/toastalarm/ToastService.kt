package com.isolity.toastalarm

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.isolity.toastalarm.model.TimeOfDay
import java.util.*

/**
 * Created by shoarai on 2017/08/16.
 */

object ToastService {
    private val TAG = ToastService::class.java.simpleName

    /**
     * Show toast.
     * @param context context
     */
    fun showToast(context: Context) {
        Log.v(TAG, "start showToast")
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

        val mAdView = view.findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().build()
        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                showToast(toast)
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
                showToast(toast)
            }
        }
        mAdView.loadAd(adRequest)
        Log.v(TAG, "end showToast")
    }

    private fun showToast(toast: Toast) {
        toast.run {
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

    fun showDebugToast(context: Context, text: String) {
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