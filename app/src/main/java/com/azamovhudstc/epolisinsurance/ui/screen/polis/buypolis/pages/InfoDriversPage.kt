package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.invisible
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.utils.within
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriversPageViewModel
import com.azamovhudstc.epolisinsurance.viewmodel.imp.InfoDriversPageViewModelImp
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {

    private val viewModel: InfoDriversPageViewModel by viewModels<InfoDriversPageViewModelImp>()
    private var indexLabels: Array<Button>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        indexLabels = index_container.children.map { it as Button }.toList().toTypedArray()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.driverIndices.collectLatest {
                indexLabels?.let { array ->
                    for (i in 0 until it) {
                        array[i].within {
                            setOnClickListener { viewModel.showDriver(i) }
                            text = (i + 1).toString()
                            visible()
                        }
                    }
                    for (i in it until 5) {
                        array[i].invisible()
                    }
                    if (it < (indexLabels?.size ?: 0)) {
                        array[it].within{
                            setOnClickListener { viewModel.addDriver() }
                            text = "+"
                            visible()
                        }
                    }
                }
            }
            viewModel.removedPage.onEach {

            }
            viewModel.errorMessage.collectLatest {

            }
        }
    }

}