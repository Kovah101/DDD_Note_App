package com.example.data.implementations

import com.example.data.daos.NoteDao
import com.example.data.mappers.NoteMapper
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepositoryImpl (
    private val noteDao: NoteDao,
    private val noteMapper: NoteMapper
) : NoteRepository {

    override suspend fun add(note: Note) {
        noteDao.add(noteMapper.toEntity(note))
    }

    override suspend fun update(note: Note) {
        noteDao.update(noteMapper.toEntity(note))
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(noteMapper.toEntity(note))
    }

    override suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    override suspend fun getNotes(): Flow<List<Note>>  = flow {
         noteDao.getNotes().map { noteMapper.toDomain(it)
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.let { noteMapper.toDomain(it) }
    }
}