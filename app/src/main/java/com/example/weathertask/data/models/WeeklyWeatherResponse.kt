package com.example.weathertask.data.models

import com.google.gson.annotations.SerializedName

data class WeeklyWeatherResponse(
    val city: CityInfo,
    val list: List<DailyWeather>
)

data class CityInfo(
    val name: String,
    val country: String
)
data class DailyWeather(
    val dt: Long,
    val main: TempInfo,
    val weather: List<WeatherCondition>,
    val visibility: Int?
)

data class TempInfo(
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class WeatherCondition(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)