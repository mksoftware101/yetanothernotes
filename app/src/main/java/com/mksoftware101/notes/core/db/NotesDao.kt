package com.mksoftware101.notes.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Insert
    fun insert(notesEntity: NotesEntity)

    @Delete
    suspend fun delete(notesEntity: NotesEntity)
}