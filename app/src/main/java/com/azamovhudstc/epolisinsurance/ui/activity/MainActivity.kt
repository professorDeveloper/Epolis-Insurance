package com.azamovhudstc.epolisinsurance.ui.activity

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.prongbang.localization.LocalizationAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : LocalizationAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.statusBarColor = Color.parseColor("#EEEEEE")
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