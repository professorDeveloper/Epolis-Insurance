package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.IntroPageAdapter
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.OnBoardingViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.OnBoardingViewModelImp
import kotlinx.android.synthetic.main.onboarding_screen.*
import kotlinx.android.synthetic.main.onboarding_screen.view.*

class OnboardScreen : Fragment(R.layout.onboarding_screen) {
    private val viewModel: OnBoardingViewModel by viewModels<OnBoardingViewModelImp>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.progressLiveData.observe(this) {
            if (it) {
                info_intro_text.gone()
                info_intro_progress.visible()
            } else {
                info_intro_progress.gone()
                info_intro_text.visible()
            }
        }
        viewModel.resultLiveData.observe(this) {
            val bundle = Bundle()
            findNavController().navigate(
                R.id.langaugeScreen,

                bundle,
                animationTransactionClearStack(R.id.onboardScreen).build()
            )

        }
        super.onCreate(savedInstanceState)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
        );

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = IntroPageAdapter(requireActivity())
        intro_pager.adapter = adapter
        initIndicator()
        initViewPager()
    }

    private fun initViewPager() {
        toolbar.setNavigationIcon(R.drawable.toolbar_bg)
        intro_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> {
                        skip_intro.visible()
                        toolbar.setNavigationIcon(R.drawable.toolbar_bg)
                        next_btn.visible()
                        last_btn.invisible()
                    }
                    1 -> {
                        skip_intro.visible()
                        toolbar.setNavigationIcon(R.drawable.back)
                        next_btn.visible()
                        last_btn.invisible()
                    }
                    2 -> {
                        toolbar.setNavigationIcon(R.drawable.back)
                        next_btn.invisible()
                        last_btn.visible()
                        skip_intro.invisible()

                    }
                }
            }
        })
        last_btn.setOnClickListener {
            viewModel.next()
        }
        next_btn.setOnClickListener {
            intro_pager.currentItem += 1
        }
        toolbar.setNavigationOnClickListener {
            intro_pager.currentItem -= 1
        }
        skip_intro.setOnClickListener {
            intro_pager.currentItem = 2
        }
    }

    private fun initIndicator() {

        onboard_indicator.apply {
            setSliderWidth(convertDpToPixel(15f, requireContext()))
            setSliderHeight(convertDpToPixel(6f, requireContext()))
            setupWithViewPager(requireView()!!.intro_pager)
        }
    }

}