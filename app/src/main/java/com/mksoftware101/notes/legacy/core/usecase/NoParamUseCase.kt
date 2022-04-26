package com.mksoftware101.notes.legacy.core.usecase

interface NoParamUseCase<out T> {
    fun run(): T
}