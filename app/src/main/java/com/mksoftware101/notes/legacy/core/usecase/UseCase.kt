package com.mksoftware101.notes.legacy.core.usecase

interface UseCase<in T, out R> {
    fun run(value: T) : R
}

interface SuspendUseCase<in T, out R> {
    suspend fun run(value: T) : R
}