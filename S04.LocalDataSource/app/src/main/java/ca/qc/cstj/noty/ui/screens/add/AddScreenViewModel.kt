package ca.qc.cstj.noty.ui.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.noty.data.database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//Si le viewmodel nécessite le context on doit hériter de AndroidViewModel à la place de ViewModel

class AddScreenViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AddUiState())
    val uiState = _uiState.asStateFlow()

    private val noteRepository = AppDatabase.getInstance(application).noteRepository()

    fun save() {

        //Lancement d'un thread/coroutine
        viewModelScope.launch {
            try {
                noteRepository.create(_uiState.value.note)
                //TODO: Message à l'utilisateur
            } catch(ex: Exception) {
                //TODO: Message à l'utilisateur
            }
        }

    }

    fun updateTitle(newTitle: String) {

        _uiState.update {
            _uiState.value.copy(
                note = _uiState.value.note.copy(title = newTitle)
            )
        }
    }

    fun updateContent(newContent: String) {
        _uiState.update {
            _uiState.value.copy(
                note = _uiState.value.note.copy(content = newContent)
            )
        }
    }

    fun updateColor(newColor: String) {
        _uiState.update {
            _uiState.value.copy(
                note = _uiState.value.note.copy(color = newColor)
            )
        }
    }

}