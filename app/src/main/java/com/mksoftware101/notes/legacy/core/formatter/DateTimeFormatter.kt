package com.mksoftware101.notes.legacy.core.formatter

import org.threeten.bp.format.DateTimeFormatter

class DateTimeFormatter {
    companion object {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    }
}