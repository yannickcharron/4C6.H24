package ca.qc.cstj.navigationdrawer.ui.screens.planets.detailplanet

import ca.qc.cstj.navigationdrawer.models.Planet

sealed class DetailPlanetUiState {
    data class Success(val planet: Planet) : DetailPlanetUiState()

    data object Loading : DetailPlanetUiState()

    data class Error(val exception : Exception) : DetailPlanetUiState()
}