package com.mksoftware101.core.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object FirebaseAnalyticsInstance {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun createInstance() {
        firebaseAnalytics = Firebase.analytics
    }
}