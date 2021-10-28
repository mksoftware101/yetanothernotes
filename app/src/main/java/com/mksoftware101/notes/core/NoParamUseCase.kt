package com.mksoftware101.notes.core

interface NoParamUseCase<out T> {
    fun run(): T
}