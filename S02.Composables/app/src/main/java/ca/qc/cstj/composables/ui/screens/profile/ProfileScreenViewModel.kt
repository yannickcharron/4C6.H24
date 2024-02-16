package ca.qc.cstj.composables.ui.screens.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun liftOff() {
        _uiState.value.pilot.fly()
        _uiState.update {
            //_pilot.fly()
            ProfileUiState(pilot = _uiState.value.pilot)
        }
    }


}