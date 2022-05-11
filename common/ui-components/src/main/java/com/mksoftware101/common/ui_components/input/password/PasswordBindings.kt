package com.mksoftware101.common.ui_components.input.password

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onPasswordChanged")
fun onPasswordChanged(view: View, callback: PasswordCallback) {
    if (view is PasswordInputText) {
        view.passwordCallback = callback
    }
}
