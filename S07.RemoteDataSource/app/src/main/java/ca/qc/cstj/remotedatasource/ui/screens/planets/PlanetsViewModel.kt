package ca.qc.cstj.remotedatasource.ui.screens.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.remotedatasource.data.repositories.PlanetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlanetsViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _uiState = MutableStateFlow(PlanetsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            planetRepository.retrieveAll().collect { planets ->
                //planets => la liste des planètes reçues du repository
                _uiState.update {
                    _uiState.value.copy(planets = planets)
                }
            }
        }
    }


}