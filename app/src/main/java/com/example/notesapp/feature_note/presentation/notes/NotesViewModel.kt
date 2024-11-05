package com.example.notesapp.feature_note.presentation.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: NoteUseCases
): ViewModel() {

    private val _state = mutableStateOf<NotesState>(NotesState())
    val state: MutableState<NotesState> = _state


    private var recentlyDeletedNote: Note? =null

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {}
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch{
                    useCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {}
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }


}