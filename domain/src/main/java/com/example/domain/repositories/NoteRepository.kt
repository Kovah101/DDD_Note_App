package com.example.domain.repositories

import com.example.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun add(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    suspend fun deleteAll()
    suspend fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
}
