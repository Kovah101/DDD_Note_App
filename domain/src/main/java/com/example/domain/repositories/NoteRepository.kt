package com.example.domain.repositories

import com.example.domain.models.Note

interface NoteRepository {
    suspend fun add(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    suspend fun getNotes(): List<Note>?
    suspend fun getNoteById(id: Long): Note?
}
