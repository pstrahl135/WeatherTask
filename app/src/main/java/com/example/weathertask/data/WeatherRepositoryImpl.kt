package com.example.weathertask.data

import android.util.Log
import com.example.weathertask.data.models.WeatherResponse
import com.example.weathertask.data.models.WeeklyWeatherResponse
import com.example.weathertask.domain.WeatherRepository

class WeatherRepositoryImpl(private val apiService: ApiService) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String): WeatherResponse {
        return apiService.getCurrentWeather(city)
    }

    override suspend fun getWeeklyWeather(city: String): WeeklyWeatherResponse {
        Log.d("WeatherRepositoryImpl", "Getting weekly weather for $city")
        return apiService.getWeeklyWeather(city)
    }
}