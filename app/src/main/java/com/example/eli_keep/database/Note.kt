package com.example.eli_keep.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters
import androidx.room.TypeConverter

@Entity(tableName = "notes")
@TypeConverters(Converters::class)
data class Note(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val title: String,
        val content: String? = null,
        val audioPath: String? = null,
        val videoPath: String? = null,
        val type: NoteType,
        val tags: List<String> = emptyList()
)
//room默认不支持
enum class NoteType {
        TEXT, AUDIO, VIDEO
}
