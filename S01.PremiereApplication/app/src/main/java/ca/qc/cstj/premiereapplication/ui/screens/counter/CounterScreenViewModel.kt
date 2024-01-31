package ca.qc.cstj.premiereapplication.ui.screens.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterScreenUiState())
    val uiState: StateFlow<CounterScreenUiState> = _uiState.asStateFlow()

    fun modify(value: Int) {
        if (_uiState.value.counter + value < 0) return

        _uiState.update {
            _uiState.value.copy(counter = it.counter.plus(value))
        }
    }

}