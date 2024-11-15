package com.example.weathertask.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Главная страница",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(onClick = {
            // Переход на экран с текущей погодой
            navController.navigate("current_weather_screen")
        }) {
            Text(text = "Показать текущую погоду")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            navController.navigate("weekly_weather_screen")
        }) {
            Text(text = "Показать погоду на неделю")
        }
    }
}