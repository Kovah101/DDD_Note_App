package com.example.ui.notedetails

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ChecklistItem
import com.example.domain.models.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NoteDetailViewModel(
    private val noteRepository: NoteRepository
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
        Log.d("Elliot", "onSaveClicked: note = ${note.value}")
        if (note.value.isEmpty()){
            Log.d("Elliot", "onSaveClicked: note is empty")
            return
        } else {
            viewModelScope.launch {
                noteRepository.add(_note.value)
            }
        }
    }

    fun onDeleteClicked() {
        viewModelScope.launch {
            noteRepository.delete(_note.value)
        }
    }

    fun onBackClicked() {
        Log.d("Elliot", "onBackClicked: ")
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
        val newItem = ChecklistItem(
            id = generateRandomId(),
            text = "",
            isChecked = false
        )
        val updatedChecklist = _note.value.checklist.toMutableList()
        updatedChecklist.add(newItem)
        _note.value = _note.value.copy(checklist = updatedChecklist)
    }

    fun onCheckboxItemChanged(item: ChecklistItem) {
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

    fun onCheckboxItemTextChanged(item: ChecklistItem, text: String) {
        val updatedChecklist = _note.value.checklist.map { checklistItem ->
            if (checklistItem.id == item.id) {
                checklistItem.copy(text = text)
            } else {
                checklistItem
            }
        }
        _note.value = _note.value.copy(checklist = updatedChecklist)
    }

    private fun Note.isEmpty(): Boolean {
        return title.isEmpty() && content.isEmpty() && checklist.isEmpty() && imageUri.isNullOrEmpty()
    }
}

