package com.example.eli_keep.repository

import androidx.annotation.WorkerThread
import com.example.eli_keep.dao.NoteDao
import com.example.eli_keep.database.Note
import kotlinx.coroutines.flow.Flow

// NoteRepository.kt
class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(note: Note): Int {
        return noteDao.delete(note)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(note: Note) {
        noteDao.update(note)
    }
}
