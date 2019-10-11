package com.example.bookmanagementsystem

import android.app.Application
import android.content.Context

/**
 * @author dizzylay
 * @date 2019-10-11
 * @email liaoyanglay@outlook.com
 */
class CommonApp: Application() {
    companion object {
        lateinit var AppContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        AppContext = this
    }
}