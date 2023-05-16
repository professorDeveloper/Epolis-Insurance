package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.databinding.FragmentAddPolisBinding
import com.azamovhudstc.epolisinsurance.databinding.FragmentPolisScreenBinding
import com.azamovhudstc.epolisinsurance.ui.adapter.SpinnerAdapter
import com.azamovhudstc.epolisinsurance.viewmodel.AddPolisViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AddPolisViewModelImp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPolis : Fragment(R.layout.fragment_add_polis) {
    private var _binding: FragmentAddPolisBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { SpinnerAdapter() }
    private val viewModel: AddPolisViewModel by viewModels<AddPolisViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addSpinnerLiveData.observe(this){
            adapter.setAdapter(it)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.polisType.adapter=adapter
    }

}