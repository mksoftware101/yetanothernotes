package com.mksoftware101.common.ui_components.input.password

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mksoftware101.common.ui_components.R
import com.mksoftware101.core.constants.Constants.DEBOUNCE_MS
import com.mksoftware101.core.validator.PasswordValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PasswordInputText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttributes: Int = 0
) : LinearLayout(context, attributeSet, defaultStyleAttributes) {

    private var passwordInputLayout: TextInputLayout? = null
    private var passwordInputExitText: TextInputEditText? = null
    private var passwordValidator: PasswordValidator? = null
    private var currentPassword: String? = null
    private var delayJob: Job? = null

    /**
     * Set coroutine scope to perform debounce while typing
     */
    var coroutineScope: CoroutineScope? = null
        get() {
            if (field == null) {
                Log.e(
                    "PasswordInputText",
                    "Coroutine scope is null. Have you forget to set coroutine scope?"
                )
            }
            return field
        }

    /**
     * Set callback to be informed, that password is valid or not. If password is invalid then return
     * null in callback.
     */
    var passwordCallback: PasswordCallback? = null

    init {
        inflate(context, R.layout.input_password, this)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        passwordInputExitText = findViewById(R.id.passwordInputEditText)
        passwordInputExitText?.doOnTextChanged { text, _, _, _ ->
            delayJob?.cancel()
            delayJob = coroutineScope?.launch {
                hideError()
                delay(DEBOUNCE_MS)
                validate(text)
            }
        }
        passwordInputExitText?.setOnFocusChangeListener { view, hasFocus ->
            val inputEditText = view as TextInputEditText
            if (!hasFocus && inputEditText.text.isNullOrBlank()) {
                showError()
            }
        }
        extractFrom(attributeSet)
        orientation = HORIZONTAL
    }

    /**
     * Show error message, that passwords are not the same
     */
    fun showPasswordsNotSameError() {
        passwordInputLayout?.error = resources.getString(R.string.passwordPasswordsNotTheSame)
    }

    /**
     * Use to highlight error only without showing error message
     *
     * Useful in Sign up screen to highlight repeat password field when password != repeatPassword
     */
    fun highlightError() {
        passwordInputLayout?.error = " "
    }

    private fun validate(text: CharSequence?) {
        val input = text.toString()
        val candidate: String? = if (passwordValidator?.isValid(input) == true) {
            input
        } else {
            showError()
            null
        }

        if (currentPassword != candidate) {
            currentPassword = candidate
            passwordCallback?.onPasswordChanged(currentPassword)
        }
    }

    private fun extractFrom(attributeSet: AttributeSet?) {
        val styledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.PasswordInputText)
        setHint(styledAttributes)
        setValidator(styledAttributes)
        styledAttributes.recycle()
    }

    private fun setValidator(styledAttributes: TypedArray) {
        val validatorVariant = PasswordValidatorVariant.from(
            styledAttributes.getInt(
                R.styleable.PasswordInputText_passwordValidator,
                PasswordValidatorVariant.VARIANT_1.value
            )
        )
        when (validatorVariant) {
            PasswordValidatorVariant.VARIANT_1 -> passwordValidator = PasswordValidator()
        }
    }

    private fun setHint(styledAttributes: TypedArray) {
        @StringRes val hintResId = styledAttributes.getResourceId(
            R.styleable.PasswordInputText_passwordHint,
            R.string.passwordInputTextPassword
        )
        passwordInputLayout?.hint = resources.getString(hintResId)
    }

    private fun hideError() {
        passwordInputLayout?.error = null
    }

    private fun showError() {
        passwordInputLayout?.error = resources.getString(R.string.passwordInputTextInvalidPassword)
    }
}