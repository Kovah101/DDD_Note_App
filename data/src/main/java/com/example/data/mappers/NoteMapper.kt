package com.example.data.mappers

import com.example.data.entities.NoteEntity
import com.example.domain.models.Note

class NoteMapper {
    fun toDomain(noteEntity: NoteEntity) = Note(
        id = noteEntity.id,
        title = noteEntity.title,
        content = noteEntity.content,
        createdAt = noteEntity.createdAt,
        updatedAt = noteEntity.updatedAt,
        isFavorite = noteEntity.isFavorite,
        isChecklist = noteEntity.isChecklist,
        checklist = noteEntity.checklist,
        imageUri = noteEntity.imageUri
    )

    fun toEntity(note: Note) = NoteEntity(
        id = note.id,
        title = note.title,
        content = note.content,
        createdAt = note.createdAt,
        updatedAt = note.updatedAt,
        isFavorite = note.isFavorite,
        isChecklist = note.isChecklist,
        checklist = note.checklist,
        imageUri = note.imageUri
    )
}