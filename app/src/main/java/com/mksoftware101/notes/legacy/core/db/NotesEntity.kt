package com.mksoftware101.notes.legacy.core.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "creation_date")
    val creationDate: String,

    val favourite: Boolean,

    val data: String
)