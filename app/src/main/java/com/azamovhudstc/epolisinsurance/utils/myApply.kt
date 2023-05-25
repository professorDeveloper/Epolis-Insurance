package com.azamovhudstc.sugurtaapp.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.regex.Matcher
import java.util.regex.Pattern


fun <T> T.myApply(block: T.() -> Unit) {
    block(this)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showSnack(view: View=requireView(), message: String, duration: Int = Toast.LENGTH_SHORT) {
    Snackbar.make(view, message, duration).show()
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
        else -> CurrentScreenEnum.LANGUAGE
    }
}

fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources: Resources = context.getResources()
    val metrics: DisplayMetrics = resources.getDisplayMetrics()
    return dp * (metrics.densityDpi / 160f)
}

fun String.parseCode(): String {
    val p: Pattern = Pattern.compile("\\b\\d{6}\\b")
    val m: Matcher = p.matcher(this)
    var code = ""
    while (m.find()) {
        code = m.group(0)
    }
    return code
}

fun String.checkPhone(): Boolean {
    val REG = "^(\\+998|998)(90|91|93|94|95|97|98|99|88)\\d{7}$"
    val pattern = Pattern.compile(REG)
    return pattern.matcher(this).find()
}

fun Bitmap.toStringWithBitmap(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
}

fun String.stringToBitmap(): Bitmap {
    val encodeByte: ByteArray = Base64.decode(this, Base64.DEFAULT)

    val inputStream: InputStream = ByteArrayInputStream(encodeByte)
    return BitmapFactory.decodeStream(inputStream)
}