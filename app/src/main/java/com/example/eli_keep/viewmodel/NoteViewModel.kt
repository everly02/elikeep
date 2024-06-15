package com.example.eli_keep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eli_keep.database.Note
import com.example.eli_keep.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.asLiveData

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    val allNotes: LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        val rowsDeleted = repository.delete(note)

    }
    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }
}