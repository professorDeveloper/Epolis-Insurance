package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.ui.adapter.DriverInfoPageItemAdapter
import com.azamovhudstc.epolisinsurance.utils.invisible
import com.azamovhudstc.epolisinsurance.utils.visible
import com.azamovhudstc.epolisinsurance.utils.within
import com.azamovhudstc.epolisinsurance.viewmodel.InfoDriversPageViewModel
import com.azamovhudstc.sugurtaapp.utils.showSnack
import kotlinx.android.synthetic.main.fragment_info_drivers_page.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InfoDriversPage : Fragment(R.layout.fragment_info_drivers_page) {

    private val SIZE = 5
    private val driverDetailsAdapter by lazy { DriverInfoPageItemAdapter(
        SIZE,
        {
            viewModel.showSuccess(it)
        },
        {
            viewModel.removeDriver(it)
        },
        childFragmentManager,
        viewLifecycleOwner.lifecycle
    )}

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
                            text = "${i + 1}"
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
                driverDetailsAdapter.removePage(it)
            }
            viewModel.errorMessage.collectLatest {
                showSnack(message = it)
            }
            viewModel.showDriver.collectLatest {
                driver_viewPager.currentItem = it
            }
        }
    }

}