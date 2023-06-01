package com.azamovhudstc.epolisinsurance.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.ContactUsItem

class ContactUsAdapter :
    ListAdapter<ContactUsItem, ContactUsAdapter.ContactUsVH>(ContactUsItemCallback) {

    inner class ContactUsVH(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: ContactUsItem) {}
    }


    object ContactUsItemCallback : DiffUtil.ItemCallback<ContactUsItem>() {
        override fun areItemsTheSame(oldItem: ContactUsItem, newItem: ContactUsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactUsItem, newItem: ContactUsItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactUsVH {
        return ContactUsVH(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_us_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactUsVH, position: Int) {
        holder.onBind(getItem(position))
    }
}