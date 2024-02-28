package ca.qc.cstj.noty.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.qc.cstj.noty.core.Constants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val idNote: Int = 0,

    @ColumnInfo(name="title")
    val title: String = "",

    @ColumnInfo(name = "content")
    val content: String = "",

    @ColumnInfo(name = "color")
    val color : String = Constants.NOTES_COLORS.random(),

    @ColumnInfo(name = "creationDate")
    val creationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)
