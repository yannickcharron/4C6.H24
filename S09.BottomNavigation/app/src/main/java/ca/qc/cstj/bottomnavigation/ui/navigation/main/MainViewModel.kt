package ca.qc.cstj.bottomnavigation.ui.navigation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: ScreenEvent.In) {


    }

    sealed interface ScreenEvent {

        //Évènements entrants
        sealed class In {

        }

        //Évènements sortants
        sealed class Out {

        }

    }

}