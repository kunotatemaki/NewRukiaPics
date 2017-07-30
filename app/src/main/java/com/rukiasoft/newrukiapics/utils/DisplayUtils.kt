package com.rukiasoft.newrukiapics.utils

import android.content.Context


/**
 * Created by Roll on 30/7/17.
 */
object DisplayUtils {

     fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

}