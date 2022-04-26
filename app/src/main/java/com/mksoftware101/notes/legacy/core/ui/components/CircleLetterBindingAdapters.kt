package com.mksoftware101.notes.legacy.core.ui.components

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("letter")
fun setLetter(view: View, letter: String) {
    val circleLetter = view as CircleLetter
    circleLetter.setLetter(letter)
}