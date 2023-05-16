package com.azamovhudstc.epolisinsurance.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.PolsItem

class PolisAdapter :
    androidx.recyclerview.widget.ListAdapter<PolsItem, PolisAdapter.PolsViewHolder>(PolsItemCallback) {

    inner class PolsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: PolsItem) {

        }
    }

    object PolsItemCallback : DiffUtil.ItemCallback<PolsItem>() {
        override fun areItemsTheSame(oldItem: PolsItem, newItem: PolsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PolsItem, newItem: PolsItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolsViewHolder {
        return PolsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.polis_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PolsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}