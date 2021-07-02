package com.mksoftware101.notes

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
//import com.mksoftware101.notes.databinding.ActivityMainBinding

interface MainActivityUiCommand {
    fun hideSplashScreen()
}

class MainActivity : AppCompatActivity(), MainActivityUiCommand {

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        this.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }

    override fun hideSplashScreen() {
//        binding.splashLayout.rootView.visibility = View.GONE
    }
}