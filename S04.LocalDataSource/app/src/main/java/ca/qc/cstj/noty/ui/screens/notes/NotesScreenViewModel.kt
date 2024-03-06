package ca.qc.cstj.noty.ui.screens.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.noty.data.database.AppDatabase
import ca.qc.cstj.noty.models.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesScreenViewModel(application: Application) : AndroidViewModel(application) {


    //Étape 1 : Rendre disponible l'état
    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState = _uiState.asStateFlow()

    //Étape 2 : Retrouver les repositories nécessaires
    private val noteRepository = AppDatabase.getInstance(application).noteRepository()

    init {
        viewModelScope.launch {
            // Version sans Flow
            // val notes = noteRepository.retrieveAll()
            noteRepository.retrieveAll().collect { notes ->
                _uiState.update {
                    _uiState.value.copy(notes = notes)
                }
            }
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            try {
                noteRepository.delete(note)
            }
            catch (_: Exception) {
                //Je n'utilise pas ex donc préférable de le renommer par _
            }
        }
    }



}