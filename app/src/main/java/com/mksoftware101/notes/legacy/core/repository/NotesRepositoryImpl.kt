package com.mksoftware101.notes.legacy.core.repository

import com.mksoftware101.notes.legacy.core.db.NotesDb
import com.mksoftware101.notes.legacy.core.db.NotesEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class NotesRepositoryImpl(private val db: NotesDb) : NotesRepository {

    override fun getDbEntityList(): Flow<List<NotesEntity>> = db.getNotesDao().getAllNotes()

    override suspend fun remove(entity: NotesEntity) {
        db.getNotesDao().delete(entity)
    }

    override suspend fun update(entity: NotesEntity) {
        Timber.d("[d] update ")
        db.getNotesDao().update(entity)
    }
}