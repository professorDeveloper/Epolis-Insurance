package com.azamovhudstc.epolisinsurance.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.utils.LocalData.setDriverCountListener
import kotlinx.android.synthetic.main.tab_item_driver.view.*


class InfoDriverAdapter : RecyclerView.Adapter<InfoDriverAdapter.InfoDriverVH>() {
    val list = ArrayList<TabModel>()
    private var checkedPosition = 0
    private lateinit var itemClickListener: ((TabModel, Int) -> Unit)
    fun setDeleteItemClickListener(listener: (TabModel, Int) -> Unit) {
        itemClickListener = listener
    }

    inner class InfoDriverVH(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: TabModel) {
            itemView.apply {
                setDriverCountListener {
                    checkedPosition=it
                    notifyDataSetChanged()
                }
                driver_tab_text.text = data.name
                if (checkedPosition == -1) {
                    tabContainer.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
                    driver_tab_text.setTextColor(Color.BLACK)
                } else {
                    if (checkedPosition == adapterPosition) {
                        tabContainer.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
                        driver_tab_text.setTextColor(Color.WHITE)
                    } else {
                        tabContainer.setBackgroundResource(R.drawable.bg_driver_tab_unselected)
                        driver_tab_text.setTextColor(Color.BLACK)
                    }
                }


                itemView.setOnClickListener {
                    itemView.apply {
                        tabContainer.setBackgroundResource(R.drawable.bg_dirver_tab_selected)
                        driver_tab_text.setTextColor(Color.WHITE)
                        if (checkedPosition != adapterPosition) {
                            notifyItemChanged(checkedPosition)
                            checkedPosition = adapterPosition
                            itemClickListener.invoke(data, adapterPosition)
                        } else {
                            itemClickListener.invoke(data, adapterPosition)
                        }
                    }
                }
            }
        }
    }

    fun addItem(item: TabModel) {
        list.add(item)
        notifyItemInserted(list.lastIndex)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position in 0 until list.size) {
            list.removeAt(position)
            notifyItemRemoved(position)
            list.onEach { data ->
                if (position + 1 <= (data.name).toInt()) {
                    data.name = (data.name.toInt() - 1).toString()
                }
            }
            notifyDataSetChanged()
        }
    }

    fun submitList(newList: ArrayList<TabModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoDriverVH {
        return InfoDriverVH(
            LayoutInflater.from(parent.context).inflate(R.layout.tab_item_driver, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InfoDriverVH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}