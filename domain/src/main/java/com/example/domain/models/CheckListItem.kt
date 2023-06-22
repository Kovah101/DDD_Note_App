package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ChecklistItem(
    val id: Int,
    val text: String,
    val isChecked: Boolean
) {
    companion object {
        fun empty() = ChecklistItem(
            id = 0,
            text = "",
            isChecked = false
        )
    }
}
