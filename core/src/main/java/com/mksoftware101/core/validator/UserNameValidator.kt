package com.mksoftware101.core.validator

class UserNameValidator : Validator {
    override fun isValid(input: String): Boolean {
        return input.isNotBlank()
    }
}