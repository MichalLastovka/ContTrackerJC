package com.example.conttrackerjc

import android.app.Application
import android.content.Context

class ConTrackerApp: Application() {
    init {
        app =this
    }
    companion object {
        private lateinit var app: ConTrackerApp
        fun getAppContext(): Context = app.applicationContext
    }
}