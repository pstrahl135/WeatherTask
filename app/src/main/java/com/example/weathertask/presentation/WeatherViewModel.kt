package com.example.weathertask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertask.data.models.WeatherResponse
import com.example.weathertask.data.models.WeeklyWeatherResponse
import com.example.weathertask.domain.CurrentWeatherUseCase
import com.example.weathertask.domain.WeeklyWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    private val weeklyWeatherUseCase: WeeklyWeatherUseCase
) : ViewModel() {

    private val _currentWeather = MutableLiveData<WeatherResponse?>()
    val currentWeather: LiveData<WeatherResponse?> = _currentWeather

    private val _weeklyWeather = MutableLiveData<WeeklyWeatherResponse?>()
    val weeklyWeather: LiveData<WeeklyWeatherResponse?> = _weeklyWeather

    private val _isCurrentWeatherLoading = MutableLiveData<Boolean>()
    val isCurrentWeatherLoading: LiveData<Boolean> = _isCurrentWeatherLoading

    private val _isWeeklyWeatherLoading = MutableLiveData<Boolean>()
    val isWeeklyWeatherLoading: LiveData<Boolean> = _isWeeklyWeatherLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadCurrentWeather(city: String) {
        _isCurrentWeatherLoading.value = true
        viewModelScope.launch {
            try {
                Log.d("WeatherViewModel", "Loading current weather for $city")
                val weather = currentWeatherUseCase.getCurrentWeather(city)
                _currentWeather.value = weather
                Log.d("WeatherViewModel", "Current weather loaded: $weather")
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
                Log.e("WeatherViewModel", "Error loading current weather: ${e.localizedMessage}")
            } finally {
                _isCurrentWeatherLoading.value = false
            }
        }
    }

    fun loadWeeklyWeather(city: String) {
        _isWeeklyWeatherLoading.value = true
        viewModelScope.launch {
            try {
                Log.d("WeatherViewModel", "Loading weekly weather for $city")
                val weeklyWeather = weeklyWeatherUseCase.execute(city)
                _weeklyWeather.value = weeklyWeather
                Log.d("WeatherViewModel", "Weekly weather loaded: $weeklyWeather")
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
                Log.e("WeatherViewModel", "Error loading weekly weather: ${e.localizedMessage}")
            } finally {
                _isWeeklyWeatherLoading.value = false
            }
        }
    }
}