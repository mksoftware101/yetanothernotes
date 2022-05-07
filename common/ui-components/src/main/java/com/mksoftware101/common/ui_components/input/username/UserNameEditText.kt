package com.mksoftware101.common.ui_components.input.username

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
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
        val rawUserNameType = attributes.getInt(R.styleable.UserNameEditText_userNameType, -1)
        attributes.recycle()

        binding = DataBindingUtil.inflate<EdittextUsernameBinding>(
            LayoutInflater.from(context),
            R.layout.edittext_username,
            this,
            true
        ).also {
            val userNameType = UserNameType.from(rawUserNameType)
            val hint = getHintBy(userNameType)
            val validator = getValidator(userNameType)
            it.viewModel = UserNameViewModel(hint, validator)
        }

        resources.getString(com.mksoftware101.common.resources.R.string.loginEmail)
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

    private fun getHintBy(userNameType: UserNameType) = when (userNameType) {
        UserNameType.USER_NAME -> context.getString(R.string.signupUsername)
        UserNameType.EMAIL -> context.getString(R.string.signupEmail)
    }
}