package com.mksoftware101.notes.core.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class CreateCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val values = ContentValues()
        values.put("id", 1)
        values.put("creation_date", 123456)
        values.put("data", "This is sample note")

        db.insert("notes_table", SQLiteDatabase.CONFLICT_IGNORE, values)
    }
}