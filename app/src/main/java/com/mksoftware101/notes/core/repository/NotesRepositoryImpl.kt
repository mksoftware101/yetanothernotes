package com.mksoftware101.notes.core.repository

import com.mksoftware101.notes.core.db.NotesDb
import com.mksoftware101.notes.core.db.NotesEntity
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val db: NotesDb) : NotesRepository {

    override fun getDbEntityList(): Flow<List<NotesEntity>> = db.getNotesDao().getAllNotes()

    override suspend fun remove(entity: NotesEntity) {
        db.getNotesDao().delete(entity)
    }

    override suspend fun update(entity: NotesEntity) {
        db.getNotesDao().update(entity)
    }
}