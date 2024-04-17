package ca.qc.cstj.navigationdrawer.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckIn(
    val scanId:String,
    val door: Int,
    val scannedDate: String = ""
)