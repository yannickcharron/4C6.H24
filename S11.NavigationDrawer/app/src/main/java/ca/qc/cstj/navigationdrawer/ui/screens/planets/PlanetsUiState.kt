package ca.qc.cstj.navigationdrawer.ui.screens.planets

import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.models.Planet

sealed class PlanetsUiState {
    data class Success(
        val planets: List<Planet>,
        val temperatureUnit: Constants.TemperatureUnit = Constants.TemperatureUnit.Celsius
    ) : PlanetsUiState()

    data object Loading : PlanetsUiState()
    data class Error(val exception : Exception) : PlanetsUiState()

}