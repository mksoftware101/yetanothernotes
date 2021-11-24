package com.mksoftware101.notes.core.repository

import com.mksoftware101.notes.core.db.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getDbEntityList(): Flow<List<NotesEntity>>
    suspend fun remove(entity: NotesEntity)
}