package com.example.bookmanagementsystem.utils

import android.content.Context.WINDOW_SERVICE
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.bookmanagementsystem.CommonApp

object UIUtils {

    fun dp2px(dp: Float): Float {
        val resources = CommonApp.AppContext.resources
        val metrics = resources.displayMetrics
        return dp * metrics.density
    }

    fun sp2px(sp: Float): Float {
        val resources = CommonApp.AppContext.resources
        val metrics = resources.displayMetrics
        return sp * metrics.scaledDensity
    }

    fun getWidth(): Int {
        val resources = CommonApp.AppContext.resources
        val metrics = resources.displayMetrics
        return metrics.widthPixels
    }

    fun getHeight(): Int {
        val resources = CommonApp.AppContext.resources
        val metrics = resources.displayMetrics
        return metrics.heightPixels
    }

    // include navigation bar height
    fun getRealHeight(): Int {
        val wm = CommonApp.AppContext.getSystemService(WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getRealMetrics(metrics)
        return metrics.heightPixels
    }

    fun getDensity(): Float {
        val resources = CommonApp.AppContext.resources
        val metrics = resources.displayMetrics
        return metrics.density
    }

    fun getStatusBarHeight(): Int {
        var height = 0
        val resources = CommonApp.AppContext.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            height = resources.getDimensionPixelSize(resourceId)
        }
        return height
    }
}