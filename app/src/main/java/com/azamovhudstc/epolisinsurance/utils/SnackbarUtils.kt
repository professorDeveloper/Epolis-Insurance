package com.azamovhudstc.epolisinsurance.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class SnackbarUtils private constructor() {
    companion object {
        fun showCustomSnackbar(view: View, message: String, drawable: Drawable?) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.WHITE)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            snackbarView.setBackgroundColor(ContextCompat.getColor(view.context,
                android.R.color.holo_orange_light
            ))
            val layoutParams = snackbarView.layoutParams
            snackbarView.layoutParams = layoutParams


            snackbar.show()
        }
    }
}
