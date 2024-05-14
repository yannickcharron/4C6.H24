package ca.qc.cstj.bottomnavigation.ui.navigation.main

import androidx.annotation.StringRes
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

    private val _eventsChannel = Channel<ScreenEvent.ToView>()
    val events = _eventsChannel

    fun onEvent(event: ScreenEvent.ToViewModel) {
        when (event) {
            is ScreenEvent.ToViewModel.OnTitleFormat -> {
                _uiState.update {
                    _uiState.value.copy(_titleFormatArgs = event.formatArgs.toList())
                }
            }

            is ScreenEvent.ToViewModel.ShowSnackbar -> {
                viewModelScope.launch {
                    _eventsChannel.send(ScreenEvent.ToView.ShowSnackbar(
                        messageId = event.messageId ,
                        actionLabel = event.actionLabel)
                    )
                }
            }

            is ScreenEvent.ToViewModel.ShowMessage -> {
                viewModelScope.launch {
                    _eventsChannel.send(ScreenEvent.ToView.ShowMessage(event.message))
                }
            }
        }
    }

    sealed interface ScreenEvent {
        sealed class ToViewModel {
            class OnTitleFormat(vararg val formatArgs: Any) : ToViewModel()
            data class ShowSnackbar(@StringRes val messageId: Int = 0, @StringRes val actionLabel: Int = 0): ToViewModel()
            data class ShowMessage(val message: String): ToViewModel()

        }

        sealed class ToView {
            data class ShowSnackbar(@StringRes val messageId: Int = 0, @StringRes val actionLabel: Int = 0): ToView()

            data class ShowMessage(val message: String): ToView()

        }
    }

}