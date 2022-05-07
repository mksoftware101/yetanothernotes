package com.mksoftware101.core.validator

import android.util.Patterns

class EmailValidator : Validator {
    override fun isValid(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}