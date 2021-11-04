package com.mksoftware101.notes.core.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.concurrent.Executors

class CreateCallback : RoomDatabase.Callback() {

    private val now = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    private val values = listOf(
        ContentValues().also {
            it.put("id", 1)
            it.put("creation_date", now.minusDays(7).format(dateFormatter).toString())
            it.put(
                "data",
                "LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day."
            )
        },
        ContentValues().also {
            it.put("id", 2)
            it.put("creation_date", now.plusDays(2).format(dateFormatter).toString())
            it.put(
                "data",
                "The ISO-8601 calendar system is the modern civil calendar system used today in most of the world."
            )
        },
        ContentValues().also {
            it.put("id", 3)
            it.put("creation_date", now.minusDays(3).format(dateFormatter).toString())
            it.put(
                "data",
                "Other date fields, such as day-of-year, day-of-week and week-of-year, can also be accessed."
            )
        },
        ContentValues().also {
            it.put("id", 4)
            it.put("creation_date", now.minusDays(10).format(dateFormatter).toString())
            it.put(
                "data",
                "However, any application that makes use of historical dates, and requires them to be accurate will find the ISO-8601 approach unsuitable."
            )
        },
        ContentValues().also {
            it.put("id", 5)
            it.put("creation_date", now.plusDays(1).format(dateFormatter).toString())
            it.put(
                "data",
                "It is equivalent to the proleptic Gregorian calendar system, in which today's rules for leap years are applied for all time."
            )
        },
        ContentValues().also {
            it.put("id", 6)
            it.put("creation_date", now.plusDays(11).format(dateFormatter).toString())
            it.put(
                "data",
                "The classes defined here represent the principle date-time concepts, including instants, durations, dates, times, time-zones and periods."
            )
        },
        ContentValues().also {
            it.put("id", 7)
            it.put("creation_date", now.minusDays(8).format(dateFormatter).toString())
            it.put(
                "data",
                "They are based on the ISO calendar system, which is the de facto world calendar following the proleptic Gregorian rules."
            )
        },
        ContentValues().also {
            it.put("id", 8)
            it.put("creation_date", now.minusDays(8).format(dateFormatter).toString())
            it.put(
                "data",
                "Each date time instance is composed of fields that are conveniently made available by the APIs."
            )
        },
        ContentValues().also {
            it.put("id", 9)
            it.put("creation_date", now.minusDays(4).format(dateFormatter).toString())
            it.put(
                "data",
                "For lower level access to the fields refer to the java.time.temporal package."
            )
        },
        ContentValues().also {
            it.put("id", 10)
            it.put("creation_date", now.plusDays(1).format(dateFormatter).toString())
            it.put(
                "data",
                "Each class includes support for printing and parsing all manner of dates and times."
            )
        },
    )

    override fun onCreate(dataBase: SupportSQLiteDatabase) {
        super.onCreate(dataBase)
        Executors.newSingleThreadExecutor().execute { fill(dataBase) }
    }

    private fun fill(db: SupportSQLiteDatabase) {
        values.forEach {
            db.insert("notes_table", SQLiteDatabase.CONFLICT_FAIL, it)
        }
    }
}