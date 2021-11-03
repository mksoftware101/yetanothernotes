package com.mksoftware101.notes.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDb : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}