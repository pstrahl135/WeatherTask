package com.example.weathertask.domain

import com.example.weathertask.data.models.WeatherResponse
import com.example.weathertask.data.models.WeeklyWeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): WeatherResponse

    suspend fun getWeeklyWeather(city: String): WeeklyWeatherResponse
}