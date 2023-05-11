package com.example.ui.notedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val noteRepository: NoteRepository,
    private val navController: NavController
) : ViewModel() {

    private val _note = MutableStateFlow(Note.empty())
    val note: StateFlow<Note> = _note

    private var currentNoteId: Int = 0

    init {
        setNoteId(currentNoteId)
    }

    fun onTitleChanged(title: String) {
        _note.value = _note.value.copy(title = title)
    }

    fun onContentChanged(content: String) {
        _note.value = _note.value.copy(content = content)
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            noteRepository.add(_note.value)
            navController.navigate(Screen.NoteList.route)
        }
    }

    fun onDeleteClicked() {
        viewModelScope.launch {
            noteRepository.delete(_note.value)
            navController.popBackStack()
        }
    }

    fun onBackClicked() {
        onSaveClicked()
    }

    fun setNoteId(id: Int) {
        if (currentNoteId != id) {
            currentNoteId = id
            viewModelScope.launch {
                noteRepository.getNoteById(id)?.let { note ->
                    _note.value = note
                }
            }
        }
    }
}