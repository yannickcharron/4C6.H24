package ca.qc.cstj.bottomnavigation.ui.screens.splash

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import ca.qc.cstj.bottomnavigation.core.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    private val timer = object : CountDownTimer(
        Constants.LOADING_DURATION,
        Constants.LOADING_TICK_INTERVAL
    ) {
        override fun onTick(millisUntilFinished: Long) {
            _uiState.update {
                _uiState.value.copy(progress = it.progress + 1)
            }
        }

        override fun onFinish() {
            _uiState.update {
                _uiState.value.copy(isFinished = true)
            }
        }
    }

    init {
        timer.start()
    }

}