package com.example.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        loadNotes()
    }

    fun onNoteSelected(noteId: Int) {
        // navigate to detail screen
    }

    fun onAddNoteClicked() {
        // navigate to detail screen with new note
    }

    private fun loadNotes() {
        viewModelScope.launch {
            kotlin.runCatching {
                _notes.value = noteRepository.getNotes()!!
            }.onFailure {
                IllegalArgumentException("Error loading notes: $it")
            }
        }
    }
}
