package ca.qc.cstj.navigationdrawer.ui.screens.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.navigationdrawer.core.ApiResult
import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.data.repositories.PlanetRepository
import ca.qc.cstj.navigationdrawer.models.Planet
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetsScreenViewModel : ViewModel() {


    private val planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow<PlanetsUiState>(PlanetsUiState.Loading)

    val uiState = _uiState.asStateFlow()

    private lateinit var _planets: List<Planet>
    private var _unit = Constants.TemperatureUnit.Celsius

    private var refreshJob : Job? = null

    init {
        refreshPlanet()
    }


    fun changeTemperatureUnit(unit: Constants.TemperatureUnit) {
        _unit = unit
        //Le update est pas nécessairement nécessaire
        _uiState.update {
            PlanetsUiState.Success(_planets, _unit)
        }
        refreshPlanet()
    }

    private fun refreshPlanet() {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            planetRepository.retrieveAllWithRefresh(_unit).collect { result ->
                _uiState.update {
                    when (result) {
                        is ApiResult.Error -> PlanetsUiState.Error(result.exception)
                        ApiResult.Loading -> PlanetsUiState.Loading
                        is ApiResult.Success -> {
                            _planets = result.data
                            PlanetsUiState.Success(_planets, _unit)
                        }
                    }
                }
            }
        }
    }

}

