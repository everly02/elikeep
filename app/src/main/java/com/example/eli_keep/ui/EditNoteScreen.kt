package com.example.eli_keep.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eli_keep.database.Note

@Composable
fun EditNoteScreen(note: Note, onSave: (Note) -> Unit, onCancel: () -> Unit) {
    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }
    var tags by remember { mutableStateOf(note.tags.joinToString(", ")) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        content?.let {
            TextField(
                value = it,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = tags,
            onValueChange = { tags = it },
            label = { Text("Tags (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = { onSave(note.copy(title = title, content = content, tags = tags.split(",").map { it.trim() })) }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}
