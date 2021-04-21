package com.mksoftware101.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    var crashTv: Button? = null
    var  fatalCrashTv : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crashTv = findViewById<Button>(R.id.textViewCrash)
        crashTv?.setOnClickListener {
            Timber.d("AAAAAA")
            Timber.e(RuntimeException("Test non fatal exception"))
        }

        fatalCrashTv= findViewById<Button>(R.id.textViewCrashFatal)
        fatalCrashTv?.setOnClickListener{
            Log.d("TAG", "def")
            throw RuntimeException("Test fatal exception")
        }
    }
    
}