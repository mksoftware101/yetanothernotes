package com.mksoftware101.notes.legacy.core.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Insert
    fun insert(notesEntity: NotesEntity)

    @Delete
    suspend fun delete(notesEntity: NotesEntity)

    @Update
    suspend fun update(notesEntity: NotesEntity)
}