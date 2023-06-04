package com.azamovhudstc.epolisinsurance.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import kotlinx.android.synthetic.main.fragment_driver_page_item.view.*

class DriverAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items = mutableListOf<TabModel>()
    private lateinit var itemClickListener: ((TabModel, Int) -> Unit)
    fun setRemoveViewPager(listener: (TabModel, Int) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_driver_page_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolder = holder as ViewHolder
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: TabModel) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    fun submitList(newList: ArrayList<TabModel>) {
        items.clear()
        items.addAll(newList)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TabModel) {
            itemView.apply {
                driver_id_title.text = "${item.name}-Водитель"
                delete_item.setOnClickListener {
                    itemClickListener.invoke(item, adapterPosition)
                }
            }


        }
    }
}
