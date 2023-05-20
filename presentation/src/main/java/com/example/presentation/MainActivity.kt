package com.example.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.domain.model.signup.UserAuth
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()
        setUserAuth()
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        hideBottomNavi(navController)
    }

    private fun hideBottomNavi(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.interviewFragment || destination.id == R.id.analysisFragment ||
                destination.id == R.id.myPageFragment
            ) {
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUserAuth() {
        mainViewModel.userAuth =
            intent.getSerializableExtra("userAuth", UserAuth::class.java) ?: UserAuth(-1, "")
    }
}