package com.mksoftware101.common.ui_components.input.username

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.mksoftware101.common.ui_components.R
import com.mksoftware101.common.ui_components.databinding.EdittextUsernameBinding

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
            it.viewModel = UserNameViewModel(userNameType)
        }

        orientation = HORIZONTAL
    }

    fun setCallback(callback: UserNameCallback) {
        binding.viewModel?.usernameCallback = callback
    }
}