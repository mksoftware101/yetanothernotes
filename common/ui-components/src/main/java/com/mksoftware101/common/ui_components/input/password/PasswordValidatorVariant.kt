package com.mksoftware101.common.ui_components.input.password

import java.lang.IllegalArgumentException

enum class PasswordValidatorVariant(val value: Int) {
    VARIANT_1(1);

    companion object {
        fun from(value: Int): PasswordValidatorVariant = when (value) {
            VARIANT_1.value -> VARIANT_1
            else -> throw IllegalArgumentException("Unknown validator variant=$value")
        }
    }
}