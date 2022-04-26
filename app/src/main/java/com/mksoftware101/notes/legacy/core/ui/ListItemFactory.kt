package com.mksoftware101.notes.legacy.core.ui

interface ListItemFactory<in T, out R> {
    fun assemble(data: T) : R
}