package ca.qc.cstj.noty.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.noty.data.repositories.SettingsRepository
import ca.qc.cstj.noty.data.repositories.SettingsRepository.PreferencesKeys.DEFAULT_NOTE_COLOR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    private val settingsRepository = SettingsRepository(application)

    init {
        viewModelScope.launch {
            settingsRepository.settings.collect { settings ->
                _uiState.update {
                    _uiState.value.copy(settings = settings)
                }
            }
        }
    }

    fun saveColor(color: String) {
        viewModelScope.launch {
            settingsRepository.save(DEFAULT_NOTE_COLOR, color)
        }
    }

    fun onNameChange(name: String) {
        _uiState.update {
            _uiState.value.copy(
                settings = _uiState.value.settings.copy(name = name)
            )
        }
    }

    fun saveName() {
        viewModelScope.launch {
            settingsRepository.save(SettingsRepository.PreferencesKeys.NAME, _uiState.value.settings.name)
        }
    }
}