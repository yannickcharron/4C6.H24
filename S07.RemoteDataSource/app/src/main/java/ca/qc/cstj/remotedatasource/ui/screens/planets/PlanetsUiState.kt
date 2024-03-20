package ca.qc.cstj.remotedatasource.ui.screens.planets

import ca.qc.cstj.remotedatasource.models.Planet

data class PlanetsUiState(
    val planets : List<Planet> = listOf()
)