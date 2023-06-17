package com.azamovhudstc.epolisinsurance.utils

import android.content.Context
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App


lateinit var viewpagerChangeListener: ((Int) -> Unit)
fun setPositionListener(listener: (Int) -> Unit) {
    viewpagerChangeListener = listener
}
fun EditText.maskCarNumber(){
    this.addTextChangedListener {
        this.addTextChangedListener(object : TextWatcher {
            private var isEditing = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Avvalgi matn o'zgarishi haqida xabar bermaslik uchun
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Matn o'zgarishida amalga oshiriladigan kod
            }

            override fun afterTextChanged(s: Editable?) {
                // Keyin bo'sh
                if (isEditing) {
                    return
                }

                isEditing = true

                val text = this@maskCarNumber.text.toString()

                // Matndan sonlarni olib tashlash
                val numericText = text.replace("[^\\d.]".toRegex(), "")

                // Matndan faqat katta harflarni olib tashlash
                val upperCaseText = text.replace("[^A-Z]".toRegex(), "")

                val formattedText = StringBuilder()

                if (numericText.length >= 3) {
                    formattedText.append(numericText.substring(0, 2))
                    formattedText.append(upperCaseText)
                    formattedText.append(numericText.substring(2))
                } else {
                    formattedText.append(numericText)
                    formattedText.append(upperCaseText)
                }

                this@maskCarNumber.setText(formattedText.toString())
                this@maskCarNumber.setSelection(formattedText.length)

                isEditing = false
            }
        })

    }
}
fun View.alphaAnim() {
    val anim = AnimationUtils.loadAnimation(
        App.instance,
        com.azamovhudstc.epolisinsurance.R.anim.alpha_anim
    ).apply {
        duration = 1500L

        fillAfter = true
    }

    startAnimation(anim)

}

fun View.setErrorSmall() {
    setBackgroundResource(com.azamovhudstc.epolisinsurance.R.drawable.bg_ceria_error)
}

fun View.setError() {
    setBackgroundResource(R.drawable.bg_error)
}

fun View.setDefault() {
    setBackgroundResource(R.drawable.bg_nomer)
}
fun View.setDefaultBig() {
    setBackgroundResource(R.drawable.bg_add_polis_edit)
}

fun View.setDefaultSmall() {
    setBackgroundResource(R.drawable.bg_ceria)
}

fun View.slideTop(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_top).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}

fun View.slideStart(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_start).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.onlyOneClick() {
    isEnabled = false
    this.postDelayed({ this@onlyOneClick.isEnabled = true }, 400)
}

fun View.slideUp(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_up).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}


fun Fragment.vibrate(time:Long){
    val vibrate = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrate.vibrate(time)
}

fun View.vibrationAnimation() {
    val vibrationAnim =
        AnimationUtils.loadAnimation(App.instance, R.anim.vibiration_anim)
    startAnimation(vibrationAnim)

}