package ca.qc.cstj.noty.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Note(
    val idNote: Int = 0,
    val title: String = "",
    val content: String = "",
    val color : String = "",
    val creationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)
