package com.azamovhudstc.sugurtaapp.utils

import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Callback
import retrofit2.Response

fun <T> T.myApply(block: T.() -> Unit) {
    block(this)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showSnack(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Snackbar.make(requireView(), message, duration).show()
}


fun AppCompatEditText.myAddTextChangedListener(block: (String) -> Unit) {
    this.addTextChangedListener {
        it?.let {
            block.invoke(it.toString())
        }
    }
}



fun AppCompatEditText.amount(): String = this.text.toString()


fun AppCompatEditText.clear() {
    setText("")
}