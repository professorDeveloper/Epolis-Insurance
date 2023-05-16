package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.databinding.FragmentAddPolisBinding
import com.azamovhudstc.epolisinsurance.databinding.FragmentPolisScreenBinding
import com.azamovhudstc.epolisinsurance.ui.adapter.SpinnerAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.addSpinnerCat
import com.azamovhudstc.epolisinsurance.viewmodel.AddPolisViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.AddPolisViewModelImp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPolis : Fragment(R.layout.fragment_add_polis) {
    private var _binding: FragmentAddPolisBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { SpinnerAdapter() }
    private val viewModel: AddPolisViewModel by viewModels<AddPolisViewModelImp>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPolisBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.addSpinnerLiveData.observe(this){
            adapter.setAdapter(it)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setAdapter(addSpinnerCat())
        binding.polisType.adapter=adapter
        binding.backAdd.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}