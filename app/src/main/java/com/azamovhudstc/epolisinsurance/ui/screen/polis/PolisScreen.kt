package com.azamovhudstc.epolisinsurance.ui.screen.polis

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.databinding.FragmentPolisScreenBinding
import com.azamovhudstc.epolisinsurance.ui.adapter.PolisCategoryAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.isBuyOrRegistered
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.tab_polis_item.view.*

class PolisScreen : Fragment() {
    private var _binding: FragmentPolisScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPolisScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appReference = AppReference(App.instance)
        val categoryAdapter = PolisCategoryAdapter(loadCat(), requireActivity())
        binding.apply {
            if (appReference.token.toString().isEmpty()) {
                isRegister.visible()
                viewPager.gone()
            } else {
                isRegister.gone()
                viewPager.visible()
            }
            viewPager.adapter = categoryAdapter
            TabLayoutMediator(tabLayout, viewPager) { _, _ ->
            }.attach()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val customView = tab?.customView
                    customView?.polisContainer?.setBackgroundResource(R.drawable.tab_item_select)
                    customView?.titleCat?.setTextColor(requireActivity().getColor(R.color.white))
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val customView = tab?.customView
                    customView?.polisContainer?.setBackgroundResource(R.drawable.tab_item_unselected)
                    customView?.titleCat?.setTextColor(requireActivity().getColor(R.color.textColor))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
            setTab()

        }
        binding.checkCodeOtpBtn.setOnClickListener {
            isBuyOrRegistered=true
            findNavController().navigate(R.id.registerScreen)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setTab() {
        binding.apply {
            val tabCount = tabLayout.tabCount
            for (i in 0 until tabCount) {


                val tabView = LayoutInflater.from(requireActivity())
                    .inflate(R.layout.tab_polis_item, null, false)
                val tab = tabLayout.getTabAt(i)


                tab?.customView = tabView
                tabView.titleCat.text = loadCat()[i]
                if (i == 0) {
                    tabView?.polisContainer?.setBackgroundResource(R.drawable.tab_item_select)
                    tabView?.titleCat?.setTextColor(requireActivity().getColor(R.color.white))
                } else {
                    tabView?.polisContainer?.setBackgroundResource(R.drawable.tab_item_unselected)
                    tabView?.titleCat?.setTextColor(requireActivity().getColor(R.color.textColor))
                }
            }

        }
    }

    private fun loadCat(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        val data = AppReference(App.instance)
        when (data.currentLanguage) {
            LanguageType.uz -> {
                arrayList.add("Hammasi")
                arrayList.add("Faollar")
                arrayList.add("Arxivlar")
            }
            LanguageType.ru -> {
                arrayList.add("Все")
                arrayList.add("Путешествия")
                arrayList.add("Архиные")
            }
        }
        return arrayList
    }

}