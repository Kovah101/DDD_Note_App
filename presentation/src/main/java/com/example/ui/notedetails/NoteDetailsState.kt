package com.example.ui.notedetails

import com.example.domain.models.Note

// TODO needless as the state is a single object
data class NoteDetailsState(
    val note: Note
) {
    companion object {
       fun default() = NoteDetailsState(
           note = Note.empty()
       )
    }
}
