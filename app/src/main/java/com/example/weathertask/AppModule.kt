package com.example.weathertask

import com.example.weathertask.data.ApiKeyInterceptor
import com.example.weathertask.data.WeatherRepositoryImpl
import com.example.weathertask.data.WeatherApi
import com.example.weathertask.domain.CurrentWeatherUseCase
import com.example.weathertask.domain.WeatherRepository
import com.example.weathertask.domain.WeeklyWeatherUseCase
import com.example.weathertask.presentation.WeatherViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WeatherApi.create() }
    single { WeatherRepositoryImpl(get()) as WeatherRepository }
    single { CurrentWeatherUseCase(get()) }
    single { WeeklyWeatherUseCase(get()) }
    viewModel { WeatherViewModel(get(), get()) }
}