package com.azamovhudstc.epolisinsurance.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.DriverItemPage

class DriverInfoPageItemAdapter(
    private val size: Int,
    private val onSuccess: (index: Int) -> Unit,
    private val onRemove: (index: Int) -> Unit,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pages = Array(size) {
        DriverItemPage().apply {
            setOnSuccess { onSuccess(it) }
            setOnRemove { onRemove(it) }
        }
    }

    override fun getItemCount() = size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

    fun removePage(index: Int) {
        for (i in index + 1 until size) {
            pages[i - 1] = pages[i]
        }
        pages.forEachIndexed { index, driverItemPage ->
            driverItemPage.apply {
                setOnSuccess { onSuccess(index) }
                setOnRemove { onRemove(index) }
            }
        }
        notifyDataSetChanged()
    }
}