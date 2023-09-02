package com.azamovhudstc.epolisinsurance.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.data.model.ContactUsItem
import com.azamovhudstc.epolisinsurance.utils.gone
import com.azamovhudstc.epolisinsurance.utils.visible
import kotlinx.android.synthetic.main.contact_us_item.view.*

class ContactUsAdapter :
    ListAdapter<ContactUsItem, ContactUsAdapter.ContactUsVH>(ContactUsItemCallback) {

    inner class ContactUsVH(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: ContactUsItem, position: Int) {
            itemView.titleContactUs.text=data.title
            itemView.contactusDesc.text=data.description

            if (!data.expanded) {
                itemView.expandedContactUsContainer.gone()
                itemView.titleContactUs.setTextColor(Color.BLACK)
                itemView.contactusContainer.setStrokeColor(Color.BLACK)
                itemView.open_contactus_more.setImageResource(R.drawable.down_arrow)
                itemView.open_contactus_more.setColorFilter(Color.BLACK)

            } else {
                itemView.expandedContactUsContainer.visible()
                itemView.titleContactUs.setTextColor(App.instance.getColor(R.color.btn_color))
                itemView.contactusContainer.setStrokeColor(App.instance.getColor(R.color.btn_color))
                itemView.open_contactus_more.setImageResource(R.drawable.collapse_open)
                itemView.open_contactus_more.setColorFilter(App.instance.getColor(R.color.btn_color))
            }
            itemView.openContactUsCollapse.setOnClickListener {
                if (data.expanded) {
                    itemView.expandedContactUsContainer.gone()
                    itemView.titleContactUs.setTextColor(Color.BLACK)
                    itemView.contactusContainer.setStrokeColor(Color.BLACK)
                    itemView.open_contactus_more.setImageResource(R.drawable.down_arrow)
                    itemView.open_contactus_more.setColorFilter(Color.BLACK)
                    data.expanded = false
                    notifyItemChanged(position)

                    notifyDataSetChanged()
                } else {
                    itemView.expandedContactUsContainer.visible()
                    itemView.titleContactUs.setTextColor(App.instance.getColor(R.color.btn_color))
                    itemView.contactusContainer.setStrokeColor(App.instance.getColor(R.color.btn_color))
                    itemView.open_contactus_more.setImageResource(R.drawable.collapse_open)
                    itemView.open_contactus_more.setColorFilter(App.instance.getColor(R.color.btn_color))
                    data.expanded = true
                    notifyItemChanged(position)

                    notifyDataSetChanged()
                }
            }
        }
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
        holder.onBind(getItem(position), position)
    }
}