package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.ui.adapter.BuyPolisAdapter
import com.azamovhudstc.epolisinsurance.utils.setPositionListener
import com.azamovhudstc.epolisinsurance.utils.viewpagerChangeListener
import com.shuhart.stepview.StepView
import com.shuhart.stepview.StepView.ANIMATION_ALL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*


@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var a = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
//        val stepView = view.findViewById<StepView>(R.id.step_view)
        initStepView(step_view)
        viewpager_buy.adapter = BuyPolisAdapter(requireActivity())

        viewpager_buy.isUserInputEnabled = false
        viewpager_buy.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position!=0){
                    step_view.animate().start()
                    step_view.done(false)
                    step_view.go(position,true)
                    step_view.currentStep
                    println("Viewpager On Changed !!! : $position")
                    println("Viewpager On Changed !!! : $position")

                }
            }


        })
        setPositionListener{
            buyPolisMainScrollView.pageScroll(View.FOCUS_UP);

            viewpager_buy.setCurrentItem(it,true)
        }
        viewpager_buy.setOnTouchListener(null);
        back_a.setOnClickListener {
            findNavController().navigate(
                R.id.mainScreen,
                null,
                NavOptions.Builder().setPopUpTo(R.id.buyPolisScreen, true).build()
            )
        }
    }

    private fun initStepView(stepView: StepView) {

        stepView.state
            .steps(object : ArrayList<String?>() {
                init {
                    add(App.instance.resources.getString(R.string.step_one))
                    add(App.instance.resources.getString(R.string.step_two))
                    add(App.instance.resources.getString(R.string.step_third))
                    add(App.instance.resources.getString(R.string.step_four))
                }
            })
            // In case you specify both steps array is chosen.
            .stepsNumber(4)
            .animationType(ANIMATION_ALL)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()
    }

}