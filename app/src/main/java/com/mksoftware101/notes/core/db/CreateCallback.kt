package com.mksoftware101.notes.core.db

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mksoftware101.notes.core.formatter.DateTimeFormatter
import org.threeten.bp.LocalDateTime
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Provider

class CreateCallback @Inject constructor(
    private val db : Provider<NotesDb>
) : RoomDatabase.Callback() {

    private val now = LocalDateTime.now()
    private val dateTimeFormatter = DateTimeFormatter.formatter

    private val values = listOf(
        NotesEntity(1, now.minusDays(7).plusHours(5).format(dateTimeFormatter).toString(), "LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day."),
        NotesEntity(2, now.minusDays(7).plusHours(5).format(dateTimeFormatter).toString(), "LocalDate is an immutable date-time object that represents a date, often viewed as year-month-day."),
        NotesEntity(3, now.minusDays(3).plusHours(3).format(dateTimeFormatter).toString(), "Other date fields, such as day-of-year, day-of-week and week-of-year, can also be accessed."),
        NotesEntity(4, now.minusDays(10).plusMinutes(7).format(dateTimeFormatter).toString(), "However, any application that makes use of historical dates, and requires them to be accurate will find the ISO-8601 approach unsuitable."),
        NotesEntity(5, now.plusDays(1).format(dateTimeFormatter).toString(), "It is equivalent to the proleptic Gregorian calendar system, in which today's rules for leap years are applied for all time."),
        NotesEntity(6, now.minusDays(11).plusHours(12).format(dateTimeFormatter).toString(), "The classes defined here represent the principle date-time concepts, including instants, durations, dates, times, time-zones and periods."),
        NotesEntity(7, now.minusDays(8).plusMinutes(10).format(dateTimeFormatter).toString(), "They are based on the ISO calendar system, which is the de facto world calendar following the proleptic Gregorian rules."),
        NotesEntity(8, now.minusDays(8).plusMinutes(10).format(dateTimeFormatter).toString(), "Each date time instance is composed of fields that are conveniently made available by the APIs."),
        NotesEntity(9, now.minusDays(4).minusMinutes(3).format(dateTimeFormatter).toString(), "For lower level access to the fields refer to the java.time.temporal package."),
        NotesEntity(10, now.plusDays(1).format(dateTimeFormatter).toString(), "Each class includes support for printing and parsing all manner of dates and times."),
        NotesEntity(11, now.plusDays(1).plusMinutes(34).format(dateTimeFormatter).toString(), "The LineNumberTable attribute is needed for disambiguating between optimized positions inside methods."),
        NotesEntity(12, now.plusDays(3).plusMinutes(55).format(dateTimeFormatter).toString(), "The SourceFile attribute is necessary for getting line numbers printed in a stack trace on a virtual machine or device."),
        NotesEntity(13, now.plusDays(9).minusMinutes(12).format(dateTimeFormatter).toString(), "The actual original source file name is not required when retracing as the mapping file contains the original source file."),
        NotesEntity(14, now.plusDays(3).minusMinutes(120).format(dateTimeFormatter).toString(), "When publishing your app on Google Play, you can upload the mapping.txt file for each version of your app."),
        NotesEntity(15, now.plusDays(12).minusMinutes(200).format(dateTimeFormatter).toString(), "Then Google Play will deobfuscate incoming stack traces from user-reported issues so you can review them in the Play Console."),
    )

    override fun onCreate(dataBase: SupportSQLiteDatabase) {
        super.onCreate(dataBase)
        Executors.newSingleThreadExecutor().execute { fill() }
    }

    private fun fill() {
        values.forEach { notesEntity ->
            db.get().getNotesDao().insert(notesEntity)
        }
    }
}