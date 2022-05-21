package com.mksoftware101.notes

import android.app.Application
import com.mksoftware101.core.datetime.AndroidThreeTenDateTime
import com.mksoftware101.core.analytics.FirebaseAnalyticsInstance
import com.mksoftware101.core.loging.TimberLogging
import com.mksoftware101.core.parse.ParseInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ParseInitializer.initialize(this)
        AndroidThreeTenDateTime.initialize(context = this)
        FirebaseAnalyticsInstance.createInstance()
        TimberLogging.initialize()
    }
}