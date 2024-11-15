package com.example.weathertask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.weathertask.presentation.AppNavHost
import com.example.weathertask.presentation.ScreenCurrentWeather
import com.example.weathertask.presentation.WeatherViewModel
import com.example.weathertask.ui.theme.WeatherTaskTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTaskTheme {

                val navController = rememberNavController()


                AppNavHost(navController = navController)
            }
        }
    }
}