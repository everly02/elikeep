package com.example.eli_keep.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.eli_keep.database.Note
import com.example.eli_keep.database.NoteType
import com.example.eli_keep.viewmodel.NoteViewModel

@Composable
fun NoteItem(note: Note, noteViewModel: NoteViewModel) {
    var isEditing by remember { mutableStateOf(false) }

    if (isEditing) {
        when (note.type) {
            NoteType.TEXT -> EditNoteScreen(
                note = note,
                onSave = {
                    noteViewModel.update(it)
                    isEditing = false
                },
                onCancel = { isEditing = false }
            )
            NoteType.AUDIO -> AudioRecordScreen(

                onSave = {
                    noteViewModel.update(note.copy(audioPath = it))
                    isEditing = false
                },
                onCancel = { isEditing = false }
            )
            NoteType.VIDEO -> VideoRecordScreen(

                onSave = {
                    noteViewModel.update(note.copy(videoPath = it))
                    isEditing = false
                },
                onCancel = { isEditing = false }
            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { isEditing = true }
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = note.title, fontWeight = FontWeight.Bold)
                when (note.type) {
                    NoteType.TEXT -> {
                        Text(text = note.content ?: "")
                        Icon(Icons.Default.Article, contentDescription = "Text Note")
                    }
                    NoteType.AUDIO -> {
                        Text(text = "Audio Note: ${note.audioPath}")
                        Icon(Icons.Default.Mic, contentDescription = "Audio Note")
                    }
                    NoteType.VIDEO -> {
                        Text(text = "Video Note: ${note.videoPath}")
                        Icon(Icons.Default.Videocam, contentDescription = "Video Note")
                    }
                }
                if (note.tags.isNotEmpty()) {
                    Text(
                        text = "Tags: ${note.tags.joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            IconButton(onClick = { noteViewModel.delete(note) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}