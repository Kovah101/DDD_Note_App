package com.example.ui.notedetails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(viewModel: NoteDetailViewModel = koinViewModel()) {
    val note by viewModel.note.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note Detail") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onBackClicked() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = note.title,
                    onValueChange = { viewModel.onTitleChanged(it) },
                    label = { Text("Title") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = note.content,
                    onValueChange = { viewModel.onContentChanged(it) },
                    label = { Text("Content") }
                )
            }
        },
        bottomBar = {
            if (note.id == 0) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onSaveClicked() }
                ) {
                    Text("Save")
                }
            } else {
                BottomAppBar(
                    content = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        ) {
                            IconButton(
                                onClick = { viewModel.onDeleteClicked() },
                                content = {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            )
                            IconButton(
                                onClick = { viewModel.onEditClicked() },
                                content = {
                                    Icon(
                                        Icons.Filled.Edit,
                                        contentDescription = "Edit"
                                    )
                                }
                            )
                        }
                    }
                )
            }
        }
    )
}