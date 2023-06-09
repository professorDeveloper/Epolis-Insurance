package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.ui.activity.MainActivity
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.position
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import kotlinx.android.synthetic.main.langauge_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class LanguageScreen : Fragment(R.layout.langauge_screen) {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        );

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        val appReference = AppReference(requireContext())
        next_btn_language.setOnClickListener {
            appReference.currentScreenEnum = CurrentScreenEnum.HOME
            findNavController().navigate(
                R.id.mainScreen,
                null,
                animationTransactionClearStack(R.id.langaugeScreen).build()
            )
        }
        uz_language_btn.setOnClickListener {
            clearWithPosition(1)
            appReference.currentLanguage = LanguageType.uz
            setLocate("uz")
            position = 1

        }
        ru_language_btn.setOnClickListener {
            clearWithPosition(2)
            appReference.currentLanguage = LanguageType.ru
            setLocate("ru")
            position = 2
        }
    }

    private fun setLocate(language: String) {
        val activity = (requireActivity() as MainActivity)
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
                next_btn_language.text = "Keyingisi"
                language_desc.text = "Impex Insurance Ilovasiga xush kelibsiz, tilni tanlang ."
                uz_correct_img.visible()
                ru_correct_img.gone()
                uz_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                ru_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
                next_btn_language.slideUp(1000, 0)
                next_btn_language.visible()
                LocalData.position = 0
            }

            2 -> {
                language_title.text = "Выберите язык"
                next_btn_language.text = "Продолжить"
                language_desc.text =
                    "Добро пожаловать в приложение Impex Insurance, выберите свой язык"
                ru_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                uz_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
                ru_correct_img.visible()
                uz_correct_img.gone()
                next_btn_language.slideUp(1000, 0)
                next_btn_language.visible()
                LocalData.position = 0
            }


        }
    }

    override fun onStart() {
        super.onStart()
        if (position != 0) {
            clearWithPosition(position)
        }
    }
}