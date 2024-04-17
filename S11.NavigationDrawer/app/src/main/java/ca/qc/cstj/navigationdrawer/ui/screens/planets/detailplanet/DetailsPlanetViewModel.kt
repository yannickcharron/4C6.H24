package ca.qc.cstj.navigationdrawer.ui.screens.planets.detailplanet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ca.qc.cstj.navigationdrawer.core.ApiResult
import ca.qc.cstj.navigationdrawer.data.repositories.PlanetRepository
import ca.qc.cstj.navigationdrawer.models.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsPlanetViewModel(href: String) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailPlanetUiState>(DetailPlanetUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val planetRepository = PlanetRepository()

    init {
        viewModelScope.launch {
            planetRepository.retrieveOne(href).collect { result ->
                _uiState.update {
                    when(result) {
                        is ApiResult.Error -> DetailPlanetUiState.Error(result.exception)
                        ApiResult.Loading -> DetailPlanetUiState.Loading
                        is ApiResult.Success -> DetailPlanetUiState.Success(result.data)
                    }
                }
            }
        }
    }

    companion object {
        fun Factory(planet: Planet): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailsPlanetViewModel(planet.href)
            }
        }

        fun Factory(href:String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailsPlanetViewModel(href)
            }
        }
    }



}