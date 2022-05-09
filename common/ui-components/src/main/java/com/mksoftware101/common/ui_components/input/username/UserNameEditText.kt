package com.mksoftware101.common.ui_components.input.username

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.mksoftware101.common.ui_components.R
import com.mksoftware101.common.ui_components.databinding.EdittextUsernameBinding
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.UserNameValidator
import com.mksoftware101.core.validator.Validator

class UserNameEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttributes: Int = 0
) : LinearLayout(context, attributeSet, defaultStyleAttributes) {

    private var binding: EdittextUsernameBinding

    init {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.UserNameEditText)
        val userNameType = UserNameType.from(
            attributes.getInt(R.styleable.UserNameEditText_userNameType, -1)
        )

        @StringRes
        val hintResId =
            attributes.getResourceId(R.styleable.UserNameEditText_hint, R.string.signupUsername)
        attributes.recycle()

        binding = DataBindingUtil.inflate<EdittextUsernameBinding>(
            LayoutInflater.from(context),
            R.layout.edittext_username,
            this,
            true
        ).also { bindings ->
            bindings.userNameEditText.hint = resources.getString(hintResId)
            bindings.viewModel = UserNameViewModel(
                validator = getValidator(userNameType),
                errorText = getErrorText(userNameType)
            )
        }

        orientation = HORIZONTAL
    }

    /**
     * Set valid/invalid username callback
     */
    fun setCallback(callback: UserNameCallback) {
        binding.viewModel?.usernameCallback = callback
    }

    private fun getValidator(userNameType: UserNameType): Validator = when (userNameType) {
        UserNameType.USER_NAME -> UserNameValidator()
        UserNameType.EMAIL -> EmailValidator()
    }

    private fun getErrorText(type: UserNameType): String = when (type) {
        UserNameType.USER_NAME -> resources.getString(R.string.userNameEditTextUserNameError)
        UserNameType.EMAIL -> resources.getString(R.string.userNameEditTextEmailError)
    }
}