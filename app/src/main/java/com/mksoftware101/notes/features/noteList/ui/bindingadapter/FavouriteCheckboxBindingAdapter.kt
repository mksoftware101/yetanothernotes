package com.mksoftware101.notes.features.noteList.ui.bindingadapter

import android.view.View
import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import timber.log.Timber

@BindingAdapter("toggleWhenError")
fun toggleWhenError(view: View, observer: ObservableBoolean) {
    observer.addOnPropertyChangedCallback(Callback(view as CheckBox))
}

class Callback(val checkbox: CheckBox) : Observable.OnPropertyChangedCallback() {
    override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
        Timber.d("[d] onPropertyChanged before: ${checkbox.isChecked}")
        checkbox.toggle()
//        checkbox.invalidate()
        Timber.d("[d] onPropertyChanged after: ${checkbox.isChecked}")
    }
}