package com.example.eli_keep.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        return value?.split(",") ?: emptyList()
    }

    @TypeConverter
    fun listToString(list: List<String>?): String {
        return list?.joinToString(",") ?: ""
    }
}