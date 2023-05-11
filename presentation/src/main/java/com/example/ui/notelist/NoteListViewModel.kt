package com.example.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val noteRepository: NoteRepository,
    private val navController: NavController
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        loadNotes()
    }

    fun onNoteSelected(noteId: Int) {
        navController.navigate(Screen.NoteDetail.route + "/$noteId")
    }

    fun onAddNoteClicked() {
        navController.navigate(Screen.NoteDetail.route + "/0")
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
