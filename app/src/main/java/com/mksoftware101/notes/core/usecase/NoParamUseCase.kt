package com.mksoftware101.notes.core.usecase

interface NoParamUseCase<out T> {
    fun run(): T
}