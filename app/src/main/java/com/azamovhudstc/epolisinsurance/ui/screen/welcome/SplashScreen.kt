package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.utils.animationTransactionClearStack
import com.azamovhudstc.epolisinsurance.utils.enums.CurrentScreenEnum
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.splash_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            powered_splash.slideUp(1000L, 0)
            delay(1200)
            val appReference = AppReference(requireContext())
            when (appReference.currentScreenEnum) {
                CurrentScreenEnum.HOME -> {
                    findNavController().navigate(
                        R.id.mainScreen,
                        null,

                        animationTransactionClearStack(R.id.splashScreen).build()
                    )
                }
                CurrentScreenEnum.LANGUAGE -> {
                    findNavController().navigate(
                        R.id.onboardScreen,
                        null,
                        animationTransactionClearStack(R.id.splashScreen).build()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            delay(500)
            val appReference = AppReference(requireContext())
            when (appReference.currentScreenEnum) {
                CurrentScreenEnum.HOME -> {
                    findNavController().navigate(
                        R.id.mainScreen,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.splashScreen, true).build()
                    )
                }
                CurrentScreenEnum.LANGUAGE -> {
                    findNavController().navigate(
                        R.id.onboardScreen,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.splashScreen, true).build()
                    )
                }
            }
        }
    }
}