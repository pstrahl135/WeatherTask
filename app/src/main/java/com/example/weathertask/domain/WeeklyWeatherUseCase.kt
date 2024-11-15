package com.example.weathertask.domain

import android.util.Log
import com.example.weathertask.data.models.WeeklyWeatherResponse

class WeeklyWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String): WeeklyWeatherResponse {
        return weatherRepository.getWeeklyWeather(city)
    }
}