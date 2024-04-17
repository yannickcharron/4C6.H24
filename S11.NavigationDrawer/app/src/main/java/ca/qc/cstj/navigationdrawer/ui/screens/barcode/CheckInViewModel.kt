package ca.qc.cstj.navigationdrawer.ui.screens.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.navigationdrawer.core.ApiResult
import ca.qc.cstj.navigationdrawer.core.Constants
import ca.qc.cstj.navigationdrawer.data.repositories.CheckInRepository
import ca.qc.cstj.navigationdrawer.models.CheckIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckInViewModel : ViewModel() {

    private val checkInRepository = CheckInRepository()

    private val _uiState = MutableStateFlow<CheckInState>(CheckInState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadCheckIn() {
        //TODO:
    }

    fun addCheckIn(codeValue: String) {
        //TODO:
    }

}