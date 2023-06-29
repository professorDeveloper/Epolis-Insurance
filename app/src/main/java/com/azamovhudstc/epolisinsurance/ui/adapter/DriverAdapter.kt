package com.azamovhudstc.epolisinsurance.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item.DriverPageItem
import com.azamovhudstc.epolisinsurance.utils.LocalData.removeDisable

class DriverAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    val items = mutableListOf<TabModel>()
    private lateinit var itemClickListener: ((TabModel, Int) -> Unit)
    fun setRemoveViewPager(listener: (TabModel, Int) -> Unit) {
        itemClickListener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        val driver =  DriverPageItem()
        driver.setRemoveClickListener { tabModel, i ->
            itemClickListener.invoke(tabModel, i)
        }
        val bundle = Bundle()
        bundle.putSerializable("data", items.get(position))
        bundle.putInt("position", position)
        bundle.putBoolean("removed", removeDisable)
        driver.arguments = bundle

        return driver
    }

    fun addItem(item: TabModel, fragment: DriverPageItem) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        println("Position:$position")
        items.removeAt(position)
        notifyItemRangeChanged(position, items.size)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    fun submitList(newList: ArrayList<TabModel>) {
        items.clear()
        items.addAll(newList)

    }

    fun submitListWithFragment(list: ArrayList<DriverPageItem>) {

    }

}
