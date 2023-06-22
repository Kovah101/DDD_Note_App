package com.example.data.typeconverters

import androidx.room.TypeConverter
import com.example.domain.models.ChecklistItem
import com.example.domain.models.Note
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChecklistConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromJson(value: String?): List<ChecklistItem> {
        return value?.let { json.decodeFromString(it) } ?: emptyList()
    }

    @TypeConverter
    fun toJson(value: List<ChecklistItem>): String {
        return json.encodeToString(value)
    }
}
