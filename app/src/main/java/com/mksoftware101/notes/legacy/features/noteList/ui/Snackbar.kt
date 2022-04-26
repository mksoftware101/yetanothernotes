package com.mksoftware101.notes.legacy.features.noteList.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mksoftware101.notes.R

class Snackbar(private val fragment: Fragment) {

    private fun showWithRetry(snackbarCallback: BaseTransientBottomBar.BaseCallback<Snackbar>?, onRetryAction : View.OnClickListener) {
        fragment.view?.let { view ->
            Snackbar.make(view, R.string.errorGetNotesList, Snackbar.LENGTH_LONG)
                .addCallback(snackbarCallback)
                .setAction(R.string.snackbarRetryBtn, onRetryAction)
                .also { it.show() }
        }
    }
}