package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.BuyPolisAdapter
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*

@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var a = 0



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val stepView = view.findViewById<StepView>(R.id.step_view)
        initStepView(stepView)
        viewpager_buy.adapter=BuyPolisAdapter(requireActivity())
        step_view.go(viewpager_buy.currentItem, true)
        back_a.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initStepView(stepView: StepView) {

        a = step_view.currentStep
        stepView.state
            .steps(arrayListOf("Step1", "Step2", "Step3", "Step4"))
            .animationType(StepView.ANIMATION_CIRCLE)
            .animationDuration(750)
            .stepsNumber(4)
            .commit()

    }

}