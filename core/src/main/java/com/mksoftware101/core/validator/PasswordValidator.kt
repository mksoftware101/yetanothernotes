package com.mksoftware101.core.validator

class PasswordValidator : Validator {
    override fun isValid(input: String): Boolean {
        return Regex(PATTERN).containsMatchIn(input)
    }

    companion object {
        /**
         * At least one upper case English letter, (?=.*?[A-Z])
         * At least one lower case English letter, (?=.*?[a-z])
         * At least one digit, (?=.*?[0-9])
         * At least one special character, (?=.*?[#?!@$%^&*-])
         * Minimum eight in length .{8,} (with the anchors)
         *
         */
        private const val PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}\$"
    }
}