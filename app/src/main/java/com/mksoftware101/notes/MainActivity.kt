package com.mksoftware101.notes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

//import com.mksoftware101.notes.databinding.ActivityMainBinding

interface MainActivityUiCommand {
    fun hideSplashScreen()
}

class MainActivity : AppCompatActivity(), MainActivityUiCommand {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navController =
            findViewById<FragmentContainerView>(R.id.navHostFragment).findNavController()
        setupBottomNavigation(bottomNavigation, navController)
        controlBottomNavigationVisibility(bottomNavigation, navController)
    }

    private fun setupBottomNavigation(
        bottomNavigation: BottomNavigationView?,
        navController: NavController
    ) {
        bottomNavigation?.setupWithNavController(navController)
    }

    private fun controlBottomNavigationVisibility(
        bottomNavigation: BottomNavigationView?,
        navController: NavController
    ) {
        navController
            .addOnDestinationChangedListener { _, destination: NavDestination, _ ->
                when (destination.id) {
                    R.id.splashFragment -> bottomNavigation?.visibility = View.GONE
                    else -> bottomNavigation?.visibility = View.VISIBLE
                }
            }
    }

    override fun onBackPressed() {
        val navController =
            findViewById<FragmentContainerView>(R.id.navHostFragment).findNavController()

        val destinationId: Int? = navController.currentDestination?.id
        destinationId?.let {
            if (it != R.id.splashFragment) {
                finish()
            }
        }
    }

    override fun hideSplashScreen() {
//        binding.splashLayout.rootView.visibility = View.GONE
    }
}