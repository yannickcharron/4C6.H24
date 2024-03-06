package ca.qc.cstj.noty.ui.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.noty.data.database.AppDatabase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.min

//Si le viewmodel nécessite le context on doit hériter de AndroidViewModel à la place de ViewModel

class AddScreenViewModel(application: Application)
    : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AddUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<ScreenEvent>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    private val noteRepository = AppDatabase.getInstance(application).noteRepository()

    fun save() {

        //Lancement d'un thread/coroutine
        viewModelScope.launch {
            try {
                if(_uiState.value.note.title.isBlank()) {
                    val content = _uiState.value.note.content
                    //val title = content.substring(0, min(content.length,40))
                    val title = content.substring(0, 40)
                    updateTitle(title)
                }
                noteRepository.create(_uiState.value.note)
                _eventsFlow.emit(ScreenEvent.NoteSaved)
            } catch(ex: Exception) {
                _eventsFlow.emit(ScreenEvent.NoteCannotBeSaved(ex))
            }
        }

    }

    fun updateTitle(newTitle: String) {
        _uiState.update {
            _uiState.value.copy(
                note = _uiState.value.note.copy(title = newTitle),
                isError = isNoteError(
                    newTitle,
                    _uiState.value.note.content
                )
            )
        }
    }

    fun updateContent(newContent: String) {
        _uiState.update {
            _uiState.value.copy(
                note = _uiState.value.note.copy(content = newContent),
                isError = isNoteError(
                    _uiState.value.note.title,
                    newContent
                )
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

    private fun isNoteError(title:String, content : String) : Boolean {
        return title.isBlank() && content.isBlank()
    }


    sealed class ScreenEvent {

        data object NoteSaved : ScreenEvent()
        data class NoteCannotBeSaved(val ex: Exception) : ScreenEvent()

    }

}