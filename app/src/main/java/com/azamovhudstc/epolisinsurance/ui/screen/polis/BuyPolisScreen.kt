package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BuyPolisScreen : Fragment() {
    private var a = 0
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
        step_view.setSteps(arrayListOf("Step1", "Step2", "Step3", "Step4"))
        stepView.state
            .animationType(StepView.ANIMATION_ALL)
            .stepsNumber(4)
            .commit()
        nextStep()

    }


    private fun nextStep() {
        a++
        lifecycleScope.launchWhenResumed {
            delay(1550)
            step_view.go(a,true)
        }
    }
}