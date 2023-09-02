package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.utils.*
import com.azamovhudstc.epolisinsurance.utils.LocalData.getPrice
import com.azamovhudstc.epolisinsurance.viewmodel.ContractScreenViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.ContractScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info_contract_page.*

@AndroidEntryPoint
class InfoContractPage : Fragment(R.layout.fragment_info_contract_page) {
    private val viewModel: ContractScreenViewModel by viewModels<ContractScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) {
            if (it) {
                contract_next_Btn_progress.visible()
                expandedContainer.invisible()
                contract_next_btn_text.gone()
                contractMainProgress.visible()
            } else {
                contract_next_Btn_progress.gone()
                contract_next_btn_text.visible()
                contractMainProgress.gone()
            }
        }
        viewModel.submitPolicyResponseLiveData.observe(this) {
            if (it.policy.eosgo_uuid != null) {
                LocalData.stepViewController.isThirdDone = true
                viewpagerChangeListener.invoke(3)
            } else {
                showSnack(requireView(), App.instance.getString(R.string.uid_error))
            }
            polisStartDate.text = it.policy.begin_date
            polisEndDate.text = it.policy.end_date
            pricePolisInfoContract.text = getPrice()
            polisTypeInfo.text = it.policy.driver_count_name

            expandedContainer.visible()
            expandedContainer.slideUp(700, 0)


        }
        viewModel.errorResponseLiveData.observe(this) {
            showSnack(requireView(), it.toString())
            expandedContainer.visible()

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)       
        cardView.slideTop(800, 0)
        expandedContainer.slideUp(700, 0)
        next_contract.slideStart(800, 0)
        next_contract.setOnClickListener {
            if (LocalData.submitPolicyRequest != null) {
                viewModel.submitPolicy(LocalData.submitPolicyRequest!!)
            } else {
                showSnack(requireView(), App.instance.getString(R.string.error_api))
            }
        }
    }
}