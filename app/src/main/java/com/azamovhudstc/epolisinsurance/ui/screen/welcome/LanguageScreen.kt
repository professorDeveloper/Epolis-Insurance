package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.activity.MainActivity
import com.azamovhudstc.epolisinsurance.utils.LanguageType
import com.azamovhudstc.epolisinsurance.utils.LocalData.position
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.slideUp
import com.azamovhudstc.epolisinsurance.utils.visible
import kotlinx.android.synthetic.main.langauge_screen.*
import kotlinx.coroutines.delay
import java.util.*

class LanguageScreen : Fragment(R.layout.langauge_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        next_btn_language.setOnClickListener { findNavController().navigate(R.id.mainScreen) }
        uz_language_btn.setOnClickListener {
            (LanguageType.uz.toString())
            clearWithPosition(1)
            setLocate(LanguageType.uz)
            position = 1
        }
        ru_language_btn.setOnClickListener {
            clearWithPosition(2)
            setLocate(LanguageType.ru)
            position = 2
        }
    }

    private fun setLocate(language: LanguageType) {
        val activity = (requireActivity() as MainActivity)
        activity.mySetLocate(Locale.forLanguageTag(language.name.toString())) {
            lifecycleScope.launchWhenResumed {
                delay(300)
            }
        }
    }

    private fun clearWithPosition(position: Int) {
        when (position) {
            1 -> {
                uz_correct_img.visible()
                ru_correct_img.gone()
                uz_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                ru_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
            }

            2 -> {
                ru_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                uz_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
                ru_correct_img.visible()
                uz_correct_img.gone()
            }


        }
    }

    override fun onStart() {
        super.onStart()
        if (position != 0) {
            when (position) {
                1 -> {
                    uz_correct_img.visible()
                    language_desc.setText(R.string.language_choose_desc)
                    ru_correct_img.gone()
                    uz_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                    ru_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
                    next_btn_language.slideUp(1000, 0)
                    next_btn_language.visible()
                }

                2 -> {
                    ru_language_btn.setBackgroundResource(R.drawable.language_select_btn)
                    uz_language_btn.setBackgroundResource(R.drawable.language_btn_bg)
                    ru_correct_img.visible()
                    uz_correct_img.gone()
                    next_btn_language.slideUp(1000, 0)
                    next_btn_language.visible()
                    position=0
                }

            }
        }
    }

}