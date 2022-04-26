package com.mksoftware101.notes.legacy.core.repository

import com.mksoftware101.notes.legacy.core.db.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getDbEntityList(): Flow<List<NotesEntity>>
    suspend fun remove(entity: NotesEntity)
    suspend fun update(entity: NotesEntity)
}