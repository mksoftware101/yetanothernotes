package com.mksoftware101.notes

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class NotesApplication : Application() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()
        setupAnalytics()
        setupLoging()
    }

    private fun setupAnalytics() {
        firebaseAnalytics = Firebase.analytics
    }

    private fun setupLoging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree())
    }
}