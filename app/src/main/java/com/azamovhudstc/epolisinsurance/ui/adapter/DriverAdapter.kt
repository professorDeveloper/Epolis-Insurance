package com.azamovhudstc.epolisinsurance.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item.DriverPageItem

class DriverAdapter(fragmentActivity: FragmentActivity,) : FragmentStateAdapter(fragmentActivity) {
    private lateinit var arrayList:ArrayList<TabModel>
    fun loadData(loadNewList:ArrayList<TabModel>){
        if (::arrayList.isInitialized){
            arrayList.clear()
            arrayList.addAll(loadNewList)
            notifyDataSetChanged()
            return
        }
        arrayList= ArrayList()
        arrayList.clear()
        arrayList.addAll(loadNewList)
        notifyDataSetChanged()
    }
    fun  addTab(tabModel: TabModel){
        if (::arrayList.isInitialized){
            arrayList.add(tabModel)
            notifyDataSetChanged()
        }
    }

    fun setTab(tabModel: TabModel,position: Int){
        if (::arrayList.isInitialized){
            arrayList.set(position,tabModel)
        }
    }
    fun removeTab(tabModel: TabModel){
        if (::arrayList.isInitialized){
            arrayList.remove(tabModel)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size

    }


    override fun createFragment(position: Int): Fragment {
        var bundle=Bundle()
        bundle.putSerializable("dataDriver",arrayList[position])
        var fragment=DriverPageItem()
        fragment.arguments=bundle
        return fragment

    }

    fun addFragment(driver: TabModel) {
        arrayList.add(driver)
        notifyItemChanged(arrayList.size)
        notifyDataSetChanged()
    }

    fun removeFragment(position: Int) {
        arrayList.removeAt(position)
        notifyDataSetChanged()
    }
}
