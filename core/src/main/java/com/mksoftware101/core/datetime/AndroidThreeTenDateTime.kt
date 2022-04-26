package com.mksoftware101.core.datetime

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

object AndroidThreeTenDateTime {
    fun initialize(context: Application) {
        AndroidThreeTen.init(context)
    }
}