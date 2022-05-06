package com.mksoftware101.common.ui_components.input.username

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onValid")
fun onValidUserName(view: View, callback: ValidUserNameCallback) {
    when (view) {
        is UserNameEditText -> {
            Log.d("TAG","[d] set callback")
            view.setCallback(callback)
        }
    }
}

