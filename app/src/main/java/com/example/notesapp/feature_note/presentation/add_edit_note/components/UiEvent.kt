package com.example.notesapp.feature_note.presentation.add_edit_note.components

sealed class UiEvent{
    data class ShowSnackBar(val message: String): UiEvent()
    object SaveNote: UiEvent()
}