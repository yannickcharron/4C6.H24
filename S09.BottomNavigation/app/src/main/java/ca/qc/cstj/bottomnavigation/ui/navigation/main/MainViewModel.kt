package ca.qc.cstj.bottomnavigation.ui.navigation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventsChannel = Channel<ScreenEvent.Out>()
    val eventChannel = _eventsChannel.consumeAsFlow()

    fun onEvent(event: ScreenEvent.In) {

        when(event) {
            is ScreenEvent.In.ShowSnackbar -> {
                //Log.d("MainEvent", event.message)
                viewModelScope.launch {
                    _eventsChannel.send(ScreenEvent.Out.ShowSnackbar(event.message))
                }
            }

            is ScreenEvent.In.FormatTitle -> {
                _uiState.update {
                    it.copy(_titleFormatArgs = event.formatArgs.toList())
                }
            }
        }

    }

    sealed interface ScreenEvent {

        //Évènements entrants
        sealed class In {
            class FormatTitle(vararg val formatArgs: Any) : In()
            data class ShowSnackbar(val message: String) : In()

        }

        //Évènements sortants vers le Composable (MainScreen)
        sealed class Out {
            data class ShowSnackbar(val message:String) : Out()
        }

    }

}