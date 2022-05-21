package com.mksoftware101.core.parse

import android.content.Context
import com.parse.Parse
import mk.software101.core.R

object ParseInitializer {
    fun initialize(context: Context) {
        val config = object {
            val applicationID = context.resources.getString(R.string.parseApplicationID)
            val clientKey = context.resources.getString(R.string.parseClientKey)
            val apiUrl = context.resources.getString(R.string.ParseApiBaseUrl)
        }
        Parse.initialize(
            Parse.Configuration.Builder(context)
                .applicationId(config.applicationID)
                .clientKey(config.clientKey)
                .server(config.apiUrl)
                .build()
        )
    }
}