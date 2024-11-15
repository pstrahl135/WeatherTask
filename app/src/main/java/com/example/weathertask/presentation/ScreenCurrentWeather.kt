package com.example.weathertask.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color


@Composable
fun ScreenCurrentWeather(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
) {
    val currentWeather by viewModel.currentWeather.observeAsState(initial = null)
    val isLoading by viewModel.isCurrentWeatherLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        // Card для рамки и контента
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Прогноз погоды за текущий день",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    currentWeather != null -> {
                        WeatherInfoRow(label = "Температура:", value = "${currentWeather!!.main.temp}°C")
                        WeatherInfoRow(label = "Ощущается как:", value = "${currentWeather!!.main.feelsLike}°C")
                        WeatherInfoRow(label = "Влажность:", value = "${currentWeather!!.main.humidity}%")
                        WeatherInfoRow(label = "Давление:", value = "${currentWeather!!.main.pressure} hPa")
                        WeatherInfoRow(label = "Видимость:", value = "${currentWeather!!.visibility} m")
                    }
                    errorMessage != null -> {
                        Text(text = errorMessage ?: "Unknown error")
                    }
                    else -> {
                        Text(text = "No data available")
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadCurrentWeather("Moscow")
    }
}

@Composable
fun WeatherInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f)
        )
        Text(text = value)
    }
}