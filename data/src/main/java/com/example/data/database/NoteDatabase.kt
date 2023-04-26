package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.daos.NoteDao
import com.example.data.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}