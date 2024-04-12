package ca.qc.cstj.bottomnavigation.ui.screens.splash

import ca.qc.cstj.bottomnavigation.core.Constants

data class SplashUiState(
    val progress: Int = 0,
    val isFinished: Boolean = false,
    val steps: Long = Constants.LOADING_DURATION / Constants.LOADING_TICK_INTERVAL
)
