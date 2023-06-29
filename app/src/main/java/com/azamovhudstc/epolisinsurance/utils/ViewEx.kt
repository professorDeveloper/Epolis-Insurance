package com.azamovhudstc.epolisinsurance.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import kotlinx.android.synthetic.main.no_connection.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

fun Fragment.animationTransactionClearStack(clearFragmentID: Int): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right)
        .setPopUpTo(clearFragmentID, true)
    return  navBuilder
}
fun Fragment.animationTransaction(): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.from_right).setExitAnim(R.anim.to_left)
        .setPopEnterAnim(R.anim.from_left).setPopExitAnim(R.anim.to_right)
    return navBuilder
}
fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

class SafeClickListener(
    private var defaultInterval: Int = 400,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun showNetworkDialog(context: FragmentActivity,container:View){
    var dialog = Dialog(context)
    container.gone()
    val inflater = LayoutInflater.from(context)
    var dialogView = inflater.inflate(R.layout.no_connection, null)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
    dialogView.try_again.setOnClickListener {
        dialogView.try_againtxt.gone()
        dialogView.try_again_progress.visible()
        context.lifecycleScope.launch {
            delay(600)
            if (hasConnection()) {
                container.visible()
                dialog.dismiss()
                dialogView.try_againtxt.visible()
                dialogView.try_again_progress.gone()

            }
            else {
                container.gone()
                dialogView.try_againtxt.visible()
                dialogView.try_again_progress.gone()
                dialog.setContentView(dialogView)
                dialog.show()
            }
        }

    }
    dialog.setContentView(dialogView)

    dialog.show()



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