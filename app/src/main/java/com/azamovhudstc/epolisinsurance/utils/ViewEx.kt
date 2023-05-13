package com.azamovhudstc.epolisinsurance.utils

import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App

fun View.alphaAnim() {
    val anim = AnimationUtils.loadAnimation(App.instance, R.anim.alpha_anim).apply {
        duration=1500L

        fillAfter=true
    }

    startAnimation(anim)
}
fun View.slideTop(animTime: Long, startOffset: Long){
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_top).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}
fun View.slideStart(animTime: Long, startOffset: Long){
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_start).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}
fun View.visible(){
    visibility=View.VISIBLE
}
fun View.gone(){
    visibility=View.GONE
}
fun View.slideUp(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_up).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}
