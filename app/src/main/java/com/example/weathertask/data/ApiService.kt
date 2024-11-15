package com.example.weathertask.data

import com.example.weathertask.data.models.WeatherResponse
import com.example.weathertask.data.models.WeeklyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    @GET("forecast")
    suspend fun getWeeklyWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("cnt") days: Int = 5
    ): WeeklyWeatherResponse
}