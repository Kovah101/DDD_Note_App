package com.example.domain.models



data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val isChecklist: Boolean,
    val checklist: List<ChecklistItem>,
    val imageUri: String?
) {

    companion object {
        fun empty() = Note(
            id = 0,
            title = "",
            content = "",
            createdAt = 0,
            updatedAt = 0,
            isFavorite = false,
            isChecklist = false,
            checklist = emptyList(),
            imageUri = null
        )
    }
}
