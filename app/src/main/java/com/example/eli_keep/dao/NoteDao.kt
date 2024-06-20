package com.example.eli_keep.dao

import androidx.room.*
import com.example.eli_keep.database.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note):Long

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)
}