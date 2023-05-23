package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.remote.request.GetVehicleRequest
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.viewmodel.BuyPolisScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.BuyPolisScreenViewModelImp
import com.shuhart.stepview.StepView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*
import kotlinx.coroutines.delay

@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var a = 0

    private var openCollapse = false
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
            error_text.text=it
            response_expanded.gone()
            searchCarTexNumber.setError()
        }
        viewModel.responseLiveData.observe(this) {
            response_expanded.visible()

            searched_car_named.text = it.result.modelName
            address_searched_car.text=it.result.division
            searched_issueYear.text=it.result.issueYear.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val stepView = view.findViewById<StepView>(R.id.step_view)
        initStepView(stepView)
        back_a.setOnClickListener {
            findNavController().popBackStack()
        }
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
                    GetVehicleRequest(
                        searchCarTexSerie.text.toString().uppercase(),
                        searchCarTexNumber.text.toString(),
                        searchCarNumber.text.toString().uppercase(),
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