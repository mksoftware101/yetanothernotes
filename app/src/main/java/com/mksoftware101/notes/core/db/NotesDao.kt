package com.mksoftware101.notes.core.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): List<NotesEntity>
}