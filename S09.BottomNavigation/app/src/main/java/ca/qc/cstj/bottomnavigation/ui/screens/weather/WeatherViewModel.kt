package ca.qc.cstj.bottomnavigation.ui.screens.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.bottomnavigation.core.ApiResult
import ca.qc.cstj.bottomnavigation.data.repositories.CurrentWeatherRepository
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

    private var searchJob : Job? = null

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            currentWeatherRepository.retrieveWithCityName(_searchTextState.value).collect { apiResult ->
                _uiState.update {
                    when(apiResult) {
                        is ApiResult.Error -> WeatherUiState.Error(apiResult.exception)
                        ApiResult.Loading -> WeatherUiState.Loading
                        is ApiResult.Success -> WeatherUiState.Success(apiResult.data)
                    }
                }
            }
        }

    }

}