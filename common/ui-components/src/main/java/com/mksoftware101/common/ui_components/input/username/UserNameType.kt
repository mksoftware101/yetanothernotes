package com.mksoftware101.common.ui_components.input.username

enum class UserNameType(val value: Int) {
    USER_NAME(1), EMAIL(2);

    companion object {
        fun from(value: Int): UserNameType = when (value) {
            USER_NAME.value -> USER_NAME
            EMAIL.value -> EMAIL
            else -> throw IllegalArgumentException("Wrong value=$value")
        }
    }
}