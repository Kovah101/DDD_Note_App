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
    val mediaUri: String?
) {
    data class ChecklistItem(
        val id: Int,
        val text: String,
        val isChecked: Boolean
    )
}
