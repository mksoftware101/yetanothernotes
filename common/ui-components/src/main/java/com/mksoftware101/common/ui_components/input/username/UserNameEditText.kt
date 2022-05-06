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
    attributes: AttributeSet? = null,
    defaultStyleAttributes: Int = 0
) : LinearLayout(context, attributes, defaultStyleAttributes) {

    private val binding = DataBindingUtil.inflate<EdittextUsernameBinding>(
        LayoutInflater.from(context),
        R.layout.edittext_username,
        this,
        true
    ).also {
        it.viewModel = UserNameViewModel()
    }

    init {
        orientation = HORIZONTAL
    }

    fun setCallback(callback: ValidUserNameCallback) {
        binding.viewModel?.validUserNameCallback = callback
    }
}