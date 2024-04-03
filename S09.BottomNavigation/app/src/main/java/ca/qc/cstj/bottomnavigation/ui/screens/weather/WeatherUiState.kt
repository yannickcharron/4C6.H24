package ca.qc.cstj.bottomnavigation.ui.screens.weather

import ca.qc.cstj.bottomnavigation.models.CurrentWeather

sealed interface WeatherUiState  {
    data object Idle : WeatherUiState
    data object Loading : WeatherUiState
    data class Success(val currentWeather : CurrentWeather) : WeatherUiState
    data class Error(val error  : Exception) : WeatherUiState
}