package com.mksoftware101.common.ui_components.input.username

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.mksoftware101.common.ui_components.R
import com.mksoftware101.core.validator.EmailValidator
import com.mksoftware101.core.validator.UserNameValidator
import com.mksoftware101.core.validator.Validator
import com.mksoftware101.common.ui_components.databinding.InputUsernameBinding

class UserNameInputText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttributes: Int = 0
) : LinearLayout(context, attributeSet, defaultStyleAttributes) {

    private var binding: InputUsernameBinding

    init {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.UserNameInputText)
        val userNameType = UserNameType.from(
            attributes.getInt(
                R.styleable.UserNameInputText_userNameType,
                UserNameType.USER_NAME.value
            )
        )

        @StringRes
        val hintResId =
            attributes.getResourceId(
                R.styleable.UserNameInputText_hint,
                R.string.userNameInputTextUserName
            )
        attributes.recycle()

        binding = DataBindingUtil.inflate<InputUsernameBinding>(
            LayoutInflater.from(context),
            R.layout.input_username,
            this,
            true
        ).also { bindings ->
            bindings.userNameEditTextContainer.hint = resources.getString(hintResId)
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
        UserNameType.USER_NAME -> resources.getString(R.string.userNameInputTextInvalidUserName)
        UserNameType.EMAIL -> resources.getString(R.string.userNameInputTextInvalidEmail)
    }
}