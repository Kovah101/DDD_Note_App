package com.example.ui.notelist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.Note
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = koinViewModel(),
    navController: NavController = rememberNavController()
) {
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Notes") }) },
        content = {
            NoteList(notes) { noteId ->
                viewModel.onNoteSelected(noteId)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddNoteClicked() },
                content = { Icon(Icons.Filled.Add, contentDescription = "Add Note") }
            )
        }
    )
}

@Composable
private fun NoteList(
    notes: List<Note>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note = note, onItemClick = onItemClick)
        }
    }
}

@Composable
private fun NoteItem(
    note: Note,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(note.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis)
        }
    }
}