package com.azamovhudstc.epolisinsurance.ui.screen.profile.language

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.activity.MainActivity
import com.azamovhudstc.epolisinsurance.utils.LocalData
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.viewmodel.imp.ProfileScreenViewModelImp
import com.prongbang.localization.LocalizationAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
@AndroidEntryPoint
class LanguageActivity : LocalizationAppCompatActivity() {
    private val viewModel by viewModels<ProfileScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        viewModel.successLanguageSaveData.observe(this,successLanguageChangeObserver)
        uz_language_btn_profile.setOnClickListener {
            clearWithPosition(1)
            setLocate("uz")
            LocalData.position = 1
            viewModel.saveLanguage(LanguageType.uz)
        }
        ru_language_btn_profile.setOnClickListener {
            clearWithPosition(2)
            setLocate("ru")
            LocalData.position = 2
            viewModel.saveLanguage(LanguageType.ru)
        }}
    private val successLanguageChangeObserver = Observer<Unit> {
        finish()

    }
    private var block: (() -> Unit)? = null

    override fun onConfigurationChanged(newConfig: Configuration) {
        block?.invoke()
        openPrepareLocalize()
        super.onConfigurationChanged(newConfig)
    }

    fun mySetLocate(locale: Locale, _block: (() -> Unit)) {
        block = _block
        setLocale(locale)
    }

    override fun onStart() {
        super.onStart()
        if (LocalData.position != 0) {
            clearWithPosition(LocalData.position)
        }
    }
    private fun setLocate(language: String) {
        val activity = (this)
        activity.mySetLocate(Locale.forLanguageTag(language)) {
            lifecycleScope.launch {
                delay(300)
            }
        }
    }

    private fun clearWithPosition(position: Int) {
        when (position) {
            1 -> {
                language_title.text = "Tilni Tanlang"
                language_desc.text = "Impex Insurance Ilovasiga xush kelibsiz, tilni tanlang ."
                uz_correct_img.visible()
                ru_correct_img.gone()
                uz_language_btn_profile.setBackgroundResource(R.drawable.language_select_btn)
                ru_language_btn_profile.setBackgroundResource(R.drawable.language_btn_bg)
                LocalData.position = 0
            }

            2 -> {
                language_title.text = "Выберите язык"
                language_desc.text =
                    "Добро пожаловать в приложение Impex Insurance, выберите свой язык"
                ru_language_btn_profile.setBackgroundResource(R.drawable.language_select_btn)
                uz_language_btn_profile.setBackgroundResource(R.drawable.language_btn_bg)
                ru_correct_img.visible()
                uz_correct_img.gone()
                LocalData.position = 0
            }


        }
    }


}