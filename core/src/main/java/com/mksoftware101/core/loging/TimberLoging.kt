package com.mksoftware101.core.loging

import mk.software101.core.BuildConfig
import timber.log.Timber

object TimberLogging {
    fun initialize() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(TimberCrashlyticsTree())
    }
}