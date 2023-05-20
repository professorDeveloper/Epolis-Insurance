package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.SearchCarAndGetPassRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.AddPolisViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.BuyPolisScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AddPolisViewModelImp
import com.azamovhudstc.epolisinsurance.viewmodel.imp.BuyPolisScreenViewModelImp
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*
import kotlinx.coroutines.delay

@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var a = 0

    private var openCollapse = true
    private val viewModel: BuyPolisScreenViewModel by viewModels<BuyPolisScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) {
            if (it) {
                seach_car_progress.visible()
                seach_car_txt.gone()
            } else {

                seach_car_txt.visible()
                seach_car_progress.gone()
            }
        }
        viewModel.errorResponseLiveData.observe(this) {
            searchCarNumber.setError()
            errorTxt.visible()
            response_expanded.gone()
            searchCarTexNumber.setError()
        }
        viewModel.responseLiveData.observe(this) {
            response_expanded.visible()

            searched_car_named.text = it.techPassport.modelName
            address_searched_car.text=it.techPassport.division.toString()
            searched_issueYear.text=it.techPassport.issueYear.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val stepView = view.findViewById<StepView>(R.id.step_view)
        initStepView(stepView)
        search_car.setOnClickListener {
            searchCarNumber.setDefault()
            errorTxt.gone()
            searchCarTexNumber.setDefault()

            if (searchCarNumber.text.toString().isEmpty() ||
                searchCarTexSerie.text.toString().isEmpty() ||
                searchCarTexNumber.text.toString().isEmpty()
            ) {

            } else {

                viewModel.searchCar(
                    SearchCarAndGetPassRequest(
                        searchCarNumber.text.toString(),
                        searchCarTexSerie.text.toString().uppercase(),
                        searchCarTexNumber.text.toString()
                    )
                )
            }
        }
        nextStep()
        openCloseCollapse.setOnClickListener {
            if (openCollapse) {
                expandedContainer.visible()
                openCollapse = !openCollapse

            } else {
                expandedContainer.gone()
                openCollapse = !openCollapse
            }
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