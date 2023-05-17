package com.azamovhudstc.sugurtaapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.regex.Matcher
import java.util.regex.Pattern


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
fun String.screenEnum(): LanguageType {
    return when (this) {
        "uz" -> LanguageType.uz
        else -> LanguageType.ru
    }
}
fun String.screenCurrentEnum(): CurrentScreenEnum {
    return when (this) {
        "HOME" -> CurrentScreenEnum.HOME
        else-> CurrentScreenEnum.LANGUAGE
    }
}
fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources: Resources = context.getResources()
    val metrics: DisplayMetrics = resources.getDisplayMetrics()
    return dp * (metrics.densityDpi / 160f)
}

fun String. parseCode():String {
    val p: Pattern = Pattern.compile("\\b\\d{6}\\b")
    val m: Matcher = p.matcher(this)
    var code = ""
    while (m.find()) {
        code = m.group(0)
    }
    return code
}