package com.azamovhudstc.epolisinsurance.ui.activity

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azamovhudstc.epolisinsurance.R
import com.prongbang.localization.LocalizationAppCompatActivity
import java.util.*

class MainActivity : LocalizationAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor= Color.parseColor("#EEEEEE")
        setContentView(R.layout.activity_main)
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        block?.invoke()
        openPrepareLocalize()
        super.onConfigurationChanged(newConfig)
    }

    fun mySetLocate(locale: Locale, _block: (() -> Unit)) {
        block = _block
        setLocale(locale)
    }

    private var block: (() -> Unit)? = null
}