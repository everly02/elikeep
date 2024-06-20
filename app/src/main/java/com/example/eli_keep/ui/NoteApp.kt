package com.example.eli_keep.ui

import android.Manifest
import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eli_keep.database.Note
import com.example.eli_keep.database.NoteType
import com.example.eli_keep.viewmodel.NoteViewModel
import dagger.hilt.android.HiltAndroidApp
import java.io.IOException
import android.media.MediaRecorder
import android.widget.Toast
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat

@HiltAndroidApp
class NoteApp : Application()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()) {
    val notes by noteViewModel.allNotes.observeAsState(emptyList())
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var selectedNoteType by remember { mutableStateOf<NoteType?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showMenu = !showMenu },
                icon = {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                },
                text = {
                    Text("Add Note")
                }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(modifier = Modifier.fillMaxSize()) {
                    NoteList(notes, noteViewModel)
                    if (showDialog && selectedNoteType != null) {
                        when (selectedNoteType) {
                            NoteType.TEXT -> EditNoteScreen(
                                note = Note(
                                    title = "",
                                    content = "",
                                    type = NoteType.TEXT
                                ),
                                onSave = {
                                    noteViewModel.insert(it)
                                    showDialog = false
                                    selectedNoteType = null
                                },
                                onCancel = {
                                    showDialog = false
                                    selectedNoteType = null
                                }
                            )
                            NoteType.AUDIO -> AudioRecordScreen(
                                onSave = {
                                    noteViewModel.insert(Note(
                                        title = "Audio Note",
                                        audioPath = it,
                                        type = NoteType.AUDIO
                                    ))
                                    showDialog = false
                                    selectedNoteType = null
                                },
                                onCancel = {
                                    showDialog = false
                                    selectedNoteType = null
                                }
                            )
                            NoteType.VIDEO -> VideoRecordScreen(
                                onSave = {
                                    noteViewModel.insert(Note(
                                        title = "Video Note",
                                        videoPath = it,
                                        type = NoteType.VIDEO
                                    ))
                                    showDialog = false
                                    selectedNoteType = null
                                },
                                onCancel = {
                                    showDialog = false
                                    selectedNoteType = null
                                }
                            )
                            null -> { /* no-op */ }
                        }
                    }
                }
            }
        }
    )

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(
            text = { Text("Add Text Note") },
            onClick = {
                selectedNoteType = NoteType.TEXT
                showDialog = true
                showMenu = false
            }
        )
        DropdownMenuItem(
            text = { Text("Add Audio Note") },
            onClick = {
                selectedNoteType = NoteType.AUDIO
                showDialog = true
                showMenu = false
            }
        )
        DropdownMenuItem(
            text = { Text("Add Video Note") },
            onClick = {
                selectedNoteType = NoteType.VIDEO
                showDialog = true
                showMenu = false
            }
        )
    }
}
