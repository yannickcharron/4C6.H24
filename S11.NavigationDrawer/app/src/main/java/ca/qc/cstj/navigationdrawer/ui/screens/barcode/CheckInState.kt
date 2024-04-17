package ca.qc.cstj.navigationdrawer.ui.screens.barcode

import ca.qc.cstj.navigationdrawer.models.CheckIn

sealed class CheckInState {
    class Error(val exception: Exception) : CheckInState()
    class Success(val checkIns: List<CheckIn>) : CheckInState()
    data object Loading : CheckInState()

}