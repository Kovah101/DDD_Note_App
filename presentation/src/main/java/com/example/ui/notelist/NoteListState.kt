package com.example.ui.notelist

import com.example.domain.models.Note

// TODO needless as the state is a single object
data class NoteListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
)
