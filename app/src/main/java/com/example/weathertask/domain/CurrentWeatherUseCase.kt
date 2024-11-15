package com.example.weathertask.domain

import com.example.weathertask.data.models.WeatherResponse

class CurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getCurrentWeather(city: String): WeatherResponse {
        return weatherRepository.getCurrentWeather(city)
    }
}