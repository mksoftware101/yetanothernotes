package com.mksoftware101.notes.base

interface NoParamUseCase<out T> {
    fun run(): T
}