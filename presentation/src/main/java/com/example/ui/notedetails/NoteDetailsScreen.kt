package com.example.ui.notedetails

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.domain.models.Note
import com.example.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = koinViewModel(),
    navController: NavController
) {
    val note by viewModel.note.collectAsState()
    val galleryLauncher: ActivityResultLauncher<String> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.onImageSelected(uri)
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note Detail") },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onBackClicked()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.NoteList.route)
                      //  viewModel.onSaveClicked()
                    }) {
                        Icon(Icons.Filled.Save, contentDescription = "Save")
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

                Spacer(modifier = Modifier.height(16.dp))

                CheckboxList(note.checklist, viewModel::onCheckboxItemChanged)

                Spacer(modifier = Modifier.height(16.dp))

                if (note.imageUri != null) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        painter = rememberAsyncImagePainter(note.imageUri),
                        contentDescription = "Note Image"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { galleryLauncher.launch("image/*") }
                ) {
                    Text("Add Image")
                }
            }
        },
        bottomBar = {
            if (note.id == 0) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                      //  viewModel.onSaveClicked()
                        navController.navigate(Screen.NoteList.route)
                    }
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
                                onClick = {
                                    viewModel.onDeleteClicked()
                                    navController.popBackStack()
                                          },
                                content = {
                                    Icon(
                                        Icons.Filled.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            )
                            IconButton(
                                onClick = { galleryLauncher.launch("image/*") },
                                content = {
                                    Icon(
                                        Icons.Filled.Photo,
                                        contentDescription = "Add Image"
                                    )
                                }
                            )
                            IconButton(
                                onClick = { viewModel.onAddCheckboxItem() },
                                content = {
                                    Icon(
                                        Icons.Filled.Checklist,
                                        contentDescription = "Add Checklist item"
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

@Composable
fun CheckboxList(
    items: List<Note.ChecklistItem>,
    onItemChanged: (Note.ChecklistItem) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = item.isChecked,
                    onCheckedChange = { onItemChanged(item.copy(isChecked = it)) },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
