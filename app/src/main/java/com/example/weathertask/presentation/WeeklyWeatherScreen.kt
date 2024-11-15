package com.example.weathertask.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathertask.data.models.DailyWeather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeeklyWeatherScreen(viewModel: WeatherViewModel, city: String = "Moscow") {

    val weeklyWeather by viewModel.weeklyWeather.observeAsState(initial = null)
    val isLoading by viewModel.isWeeklyWeatherLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.loadWeeklyWeather(city)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Прогноз погоды на неделю", style = MaterialTheme.typography.h5)


            if (isLoading) {
                Log.d("WeeklyWeatherScreen", "Loading weekly weather data")
                Text(text = "Загрузка...", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (errorMessage != null) {
                Log.e("WeeklyWeatherScreen", "Error: $errorMessage")
                Text(
                    text = errorMessage ?: "Произошла ошибка",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else if (weeklyWeather == null) {
                Log.d("WeeklyWeatherScreen", "No data available for weekly weather")
                Text(text = "Нет данных", modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Log.d("WeeklyWeatherScreen", "Displaying weekly weather data")

                WeatherList(weeklyWeather?.list ?: emptyList())
            }
        }
    }
}

// Компонент для отображения списка погоды
@Composable
fun WeatherList(weeklyWeather: List<DailyWeather>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(weeklyWeather) { dailyWeather ->
            WeatherItem(dailyWeather)
        }
    }
}

// Компонент для отображения погоды для одного дня
@Composable
fun WeatherItem(dailyWeather: DailyWeather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(
            text = "День недели: ${formatDayOfWeek(dailyWeather.dt)}",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Температура: ${dailyWeather.main.temp}°C")
        Text(text = "Ощущается как: ${dailyWeather.main.temp}°C")  // Используем значение temp еще раз, замените на "feels_like" если оно есть
        Text(text = "Влажность: ${dailyWeather.main.humidity}%")
        Text(text = "Давление: ${dailyWeather.main.pressure} гПа")
        Text(text = "Видимость: ${dailyWeather.visibility ?: "Не указано"} м")

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = dailyWeather.weather.firstOrNull()?.description.orEmpty(),
            style = MaterialTheme.typography.body2
        )
    }
}

// Форматирование дня недели
@Composable
fun formatDayOfWeek(timestamp: Long): String {
    val date = Date(timestamp * 1000)  // Преобразование в миллисекунды
    val format = SimpleDateFormat("EEEE", Locale.getDefault())
    return format.format(date)
}

// Форматирование даты в читаемую строку
@Composable
fun formatDate(dt: Long): String {
    val dateFormat = java.text.SimpleDateFormat("dd MMM, yyyy", java.util.Locale.getDefault())
    return dateFormat.format(java.util.Date(dt * 1000)) // Преобразуем из секунд в миллисекунды
}