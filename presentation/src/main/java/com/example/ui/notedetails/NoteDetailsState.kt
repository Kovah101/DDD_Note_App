package com.example.ui.notedetails

import com.example.domain.models.Note

data class NoteDetailsState(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val isChecklist: Boolean,
    val checklist: List<Note.ChecklistItem>,
    val imageUri: String?
)
