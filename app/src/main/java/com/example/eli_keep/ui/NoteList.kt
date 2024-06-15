package com.example.eli_keep.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.eli_keep.database.Note
import com.example.eli_keep.viewmodel.NoteViewModel

@Composable
fun NoteList(notes: List<Note>, noteViewModel: NoteViewModel) {
    LazyColumn {
        items(notes) { note ->
            NoteItem(note, noteViewModel)
        }
    }
}