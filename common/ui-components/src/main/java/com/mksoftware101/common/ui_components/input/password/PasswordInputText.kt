package com.mksoftware101.common.ui_components.input.password

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
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

    /**
     * Set callback to be informed, that password is valid or not. If password is invalid then return
     * null in callback.
     */
    var passwordCallback: PasswordCallback? = null

    init {
        doSetupBy(attributeSet)
        inflate(context, R.layout.input_password, this)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        passwordInputExitText = findViewById(R.id.passwordInputEditText)
        passwordInputExitText?.doOnTextChanged { text, _, _, _ ->
            delayJob?.cancel()
            delayJob = coroutineScope?.launch {
                hideError()
                delay(DEBOUNCE_MS)
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
        }
        orientation = HORIZONTAL
    }

    private fun doSetupBy(attributeSet: AttributeSet?) {
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
            R.string.signupPassword
        )
        passwordInputLayout?.hint = resources.getString(hintResId)
    }

    private fun showError() {
        passwordInputLayout?.error = resources.getString(R.string.passwordEditTextError)
    }

    private fun hideError() {
        passwordInputLayout?.error = null
    }
}