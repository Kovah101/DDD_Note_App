package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.daos.NoteDao
import com.example.data.entities.NoteEntity
import com.example.data.typeconverters.ChecklistConverter

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(ChecklistConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}