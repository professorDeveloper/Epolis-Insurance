package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.alphaAnim
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : Fragment(R.layout.fragment_splash_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            splash_logo.alphaAnim()
            powered_splash.slideUp(1000L,0)
            delay(1200)
        }
    }
}