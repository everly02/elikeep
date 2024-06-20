package com.example.eli_keep.repository

import androidx.annotation.WorkerThread
import com.example.eli_keep.dao.NoteDao
import com.example.eli_keep.database.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// NoteRepository.kt
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}