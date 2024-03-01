package ca.qc.cstj.noty.ui.screens.notes

import ca.qc.cstj.noty.models.Note

data class NotesUiState(
    val notes : List<Note> = listOf()
)