package com.example.ui.notelist

import com.example.domain.models.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
)
