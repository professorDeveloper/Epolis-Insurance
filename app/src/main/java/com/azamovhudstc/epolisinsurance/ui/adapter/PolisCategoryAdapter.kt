package com.azamovhudstc.epolisinsurance.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.polis.pages.AllPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.pages.ArchivePage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.pages.WorkingPage


class PolisCategoryAdapter(var arrayList: ArrayList<String>, fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {


    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WorkingPage()
            1 -> AllPage()
            else -> ArchivePage()
        }

    }
}