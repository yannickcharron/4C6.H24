package ca.qc.cstj.composables.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.composables.data.Data
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeditationScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MeditationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            _uiState.value.copy(
                name = "Alain",
                tags = Data.meditationTags,
                selectedTag = Data.meditationTags.random(),
                currentMeditation = Data.meditations.random(),
                featuresMeditations = Data.meditations
            )
        }
        shuffleMeditation()
    }


    fun startMeditation() {
        _uiState.update {
            _uiState.value.copy(
                currentMeditation = Data.meditations.random()
            )
        }
    }

    private fun shuffleMeditation() {
        viewModelScope.launch {
            while (true) {
                _uiState.update {
                    _uiState.value.copy(
                        featuresMeditations = Data.meditations.shuffled()
                    )
                }
                delay(15000L)
            }
        }
    }


}