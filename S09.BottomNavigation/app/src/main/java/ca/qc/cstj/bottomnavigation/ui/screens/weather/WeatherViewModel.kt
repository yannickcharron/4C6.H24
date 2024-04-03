package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.CurrentWeatherRepository
import ca.qc.cstj.bottomnavigation.models.CurrentWeather
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _searchTextState = mutableStateOf("")
    val searchTextState : State<String> = _searchTextState

    private val currentWeatherRepository = CurrentWeatherRepository()


    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun search() {

    }

}