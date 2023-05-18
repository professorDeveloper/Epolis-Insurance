package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.slideTop
import com.azamovhudstc.epolisinsurance.utils.slideUp
import com.azamovhudstc.epolisinsurance.utils.visible
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*
import kotlinx.coroutines.delay

class BuyPolisScreen : Fragment() {
    private var a = 0
    private var openCollapse=true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_polis_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val stepView = view.findViewById<StepView>(R.id.step_view)

        a = step_view.currentStep
        stepView.state
            .steps(arrayListOf("Step1", "Step2", "Step3", "Step4"))
            .animationType(StepView.ANIMATION_CIRCLE)
            .animationDuration(750)
            .stepsNumber(4)
            .commit()
        nextStep()

        openCloseCollapse.setOnClickListener {
            if (openCollapse){
                expandedContainer.visible()
                openCollapse=!openCollapse

            }
            else{
                expandedContainer.gone()
                openCollapse=!openCollapse
            }
        }
    }


    private fun nextStep() {
        a++
        lifecycleScope.launchWhenResumed {
            delay(1550)
            step_view.go(a, true)
            if (step_view.currentStep == 1) {

            }

        }
    }
}