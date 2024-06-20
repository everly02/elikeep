package com.example.eli_keep.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.eli_keep.database.Note
import com.example.eli_keep.viewmodel.NoteViewModel

@Composable
fun NoteListScreen(noteViewModel: NoteViewModel, navController: NavHostController) {
    val notes by noteViewModel.allNotes.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        NoteList(notes, noteViewModel)
    }
}




