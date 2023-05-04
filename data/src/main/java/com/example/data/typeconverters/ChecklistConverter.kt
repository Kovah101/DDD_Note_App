package com.example.data.typeconverters

import androidx.room.TypeConverter
import com.example.domain.models.Note
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChecklistConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromJson(value: String?): List<Note.ChecklistItem> {
        return value?.let { json.decodeFromString(it) } ?: emptyList()
    }

    @TypeConverter
    fun toJson(value: List<Note.ChecklistItem>): String {
        return json.encodeToString(value)
    }
}
