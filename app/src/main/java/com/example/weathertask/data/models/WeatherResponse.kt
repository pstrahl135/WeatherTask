package com.example.weathertask.data.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val main: MainInfo,
    val weather: List<WeatherDescription>,
    val visibility: Int
)

data class MainInfo(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class WeatherDescription(
    @SerializedName("description") val description: String
)
