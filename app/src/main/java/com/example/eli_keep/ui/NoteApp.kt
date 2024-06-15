package com.example.eli_keep.ui

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.eli_keep.database.Note
import com.example.eli_keep.database.NoteType
import com.example.eli_keep.viewmodel.NoteViewModel

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp : Application()


@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val notes by noteViewModel.allNotes.observeAsState(emptyList())
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        NoteList(notes, noteViewModel)
        FloatingActionButton(onClick = {
            noteViewModel.insert(
                Note(
                title = "Sample Note",
                content = "This is a sample note",
                type = NoteType.TEXT
            )
            )
        }) {
            Text("+")
        }
    }
}
