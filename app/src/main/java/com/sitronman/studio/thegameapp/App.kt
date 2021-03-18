package com.sitronman.studio.thegameapp

import android.app.Application
import com.sitronman.studio.thegameapp.common.ApplicationManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationManager.init()
    }
}