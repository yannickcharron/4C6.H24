package ca.qc.cstj.noty.ui.screens.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.noty.data.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            val notes = noteRepository.retrieveAll()
            _uiState.update {
                _uiState.value.copy(notes = notes)
            }
        }
    }



}