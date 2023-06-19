package com.example.ui.notedetails

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NoteDetailViewModel(
    private val noteRepository: NoteRepository,
    private val navController: NavController
) : ViewModel() {

    private val _note = MutableStateFlow(Note.empty())
    val note: StateFlow<Note> = _note

    private var currentNoteId: Int = 0

    init {
        setNoteId(currentNoteId)
    }

    fun onTitleChanged(title: String) {
        _note.value = _note.value.copy(title = title)
    }

    fun onContentChanged(content: String) {
        _note.value = _note.value.copy(content = content)
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            noteRepository.add(_note.value)
            navController.navigate(Screen.NoteList.route)
        }
    }

    fun onDeleteClicked() {
        viewModelScope.launch {
            noteRepository.delete(_note.value)
            navController.popBackStack()
        }
    }

    fun onBackClicked() {
        onSaveClicked()
    }

    fun setNoteId(id: Int) {
        if (currentNoteId != id) {
            currentNoteId = id
            viewModelScope.launch {
                noteRepository.getNoteById(id)?.let { note ->
                    _note.value = note
                }
            }
        }
    }

    fun onAddCheckboxItem() {
        val newItem = Note.ChecklistItem(
            id = generateRandomId(),
            text = "",
            isChecked = false
        )
        val updatedChecklist = _note.value.checklist.toMutableList()
        updatedChecklist.add(newItem)
        _note.value = _note.value.copy(checklist = updatedChecklist)
    }

    fun onCheckboxItemChanged(item: Note.ChecklistItem) {
        val updatedChecklist = _note.value.checklist.map { checklistItem ->
            if (checklistItem.id == item.id) {
                checklistItem.copy(isChecked = item.isChecked)
            } else {
                checklistItem
            }
        }
        _note.value = _note.value.copy(checklist = updatedChecklist)
    }

    fun onImageSelected(uri: Uri?) {
        if (uri == null) return
        val imagePath = uri.toString()
        _note.value = _note.value.copy(imageUri = imagePath)
    }

    private fun generateRandomId(): Int {
        val timestamp = System.currentTimeMillis()
        val randomSuffix = Random.nextInt(1000)
        return (timestamp + randomSuffix).toInt()
    }
}