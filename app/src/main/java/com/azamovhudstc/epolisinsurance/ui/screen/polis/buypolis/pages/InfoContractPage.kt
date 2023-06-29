package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.viewpagerChangeListener
import kotlinx.android.synthetic.main.fragment_info_contract_page.*


class InfoContractPage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_contract_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_contract.setOnClickListener {
            viewpagerChangeListener.invoke(3)
        }
    }
}