package com.mksoftware101.common.ui_components.input.username

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onUserNameChanged")
fun onUserNameChanged(view: View, callback: UserNameCallback) {
    if (view is UserNameEditText) {
        view.setCallback(callback)
    }
}

