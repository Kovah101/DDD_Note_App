package com.example.ui.notelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            Log.d("NoteListViewModel", "loading notes")
            kotlin.runCatching {
                noteRepository.getNotes().collectLatest { notes->
                    Log.d("NoteListViewModel", "loadNotes: $notes")
                    _notes.value = notes

                }
            }.onFailure {
                IllegalArgumentException("Error loading notes: $it")
            }
        }
    }
}
