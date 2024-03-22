package ca.qc.cstj.remotedatasource.ui.screens.planets

import ca.qc.cstj.remotedatasource.models.Planet

sealed class PlanetsUiState{
    data object Loading : PlanetsUiState()
    data class Error(val ex:Exception): PlanetsUiState()
    data class Success(val planets : List<Planet> = listOf()) : PlanetsUiState()
}



