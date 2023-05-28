package com.azamovhudstc.epolisinsurance.utils

import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem

 lateinit var viewpagerChangeListener: ((Int) -> Unit)
    fun setPositionListener(listener:(Int)->Unit){
    viewpagerChangeListener=listener
}

fun View.alphaAnim() {
    val anim = AnimationUtils.loadAnimation(App.instance, R.anim.alpha_anim).apply {
        duration=1500L

        fillAfter=true
    }

    startAnimation(anim)

}

fun View.setErrorSmall(){
    setBackgroundResource(R.drawable.bg_ceria_error)
}
fun  View.setError(){
    setBackgroundResource(R.drawable.bg_error)
}
fun  View.setDefault(){
    setBackgroundResource(R.drawable.bg_add_polis_edit)
}
fun  View.setDefaultSmall(){
    setBackgroundResource(R.drawable.bg_ceria)
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
fun View.invisible(){
    visibility=View.INVISIBLE
}
fun View.slideUp(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(App.instance, R.anim.slide_up).apply {
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }
    startAnimation(slideUp)
}
