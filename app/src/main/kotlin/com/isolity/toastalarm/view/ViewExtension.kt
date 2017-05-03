package com.isolity.toastalarm.view

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by shoarai on 2017/05/03.
 */

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}