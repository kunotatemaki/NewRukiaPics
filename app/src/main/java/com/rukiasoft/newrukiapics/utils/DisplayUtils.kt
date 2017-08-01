package com.rukiasoft.newrukiapics.utils

import android.content.Context
import android.R.attr.x
import android.app.Activity
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager




/**
 * Created by Roll on 30/7/17.
 */
object DisplayUtils {

     fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    // region Utility Methods
    fun dp2px(context: Context, dp: Int): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        val displaymetrics = DisplayMetrics()
        display.getMetrics(displaymetrics)

        return (dp * displaymetrics.density + 0.5f).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        val size = Point()
        (context as Activity).windowManager.defaultDisplay.getSize(size)
        return size.x
    }

}