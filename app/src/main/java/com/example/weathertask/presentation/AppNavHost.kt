package com.example.weathertask.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        composable("current_weather_screen") {
            val weatherViewModel: WeatherViewModel = getViewModel()
            ScreenCurrentWeather(viewModel = weatherViewModel)
        }
        composable("weekly_weather_screen") {
            val weatherViewModel: WeatherViewModel = getViewModel()
            WeeklyWeatherScreen(viewModel = weatherViewModel)
        }
    }
}