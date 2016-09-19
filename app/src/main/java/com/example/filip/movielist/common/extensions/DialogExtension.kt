package com.example.filip.movielist.common.extensions

import android.support.v7.app.AlertDialog

/**
 * Created by Filip Babic @cobe
 */


fun AlertDialog.showIfPossible() {
    if (!isShowing) {
        show()
    }
}

fun AlertDialog.dismissIfPossible() {
    if (isShowing && ownerActivity != null && !ownerActivity.isDestroyed) {
        dismiss()
    }
}