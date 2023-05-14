package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.alphaAnim
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.splash_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            splash_logo.alphaAnim()
            powered_splash.slideUp(1000L,0)
            delay(1200)
            findNavController().navigate(R.id.langaugeScreen)
        }
    }
}