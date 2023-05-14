package com.azamovhudstc.epolisinsurance.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.HomeBottomItem
import kotlinx.android.synthetic.main.home_bottom_product_item.view.*

class HomeBottomRecycleAdapter : RecyclerView.Adapter<HomeBottomRecycleAdapter.CategoryVh>() {
    private val list = ArrayList<HomeBottomItem>()
    private var checkedPosition = 0
    private lateinit var itemClickListener: ((HomeBottomItem) -> Unit)

    inner class CategoryVh(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: HomeBottomItem) {
            itemView.item_image.setImageResource(data.image)
            itemView.item_title.text=data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVh {
        return CategoryVh(
            LayoutInflater.from(parent.context).inflate(R.layout.home_bottom_product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryVh, position: Int) {
        holder.onBind(list[position])
    }

    fun submitList(newList: List<HomeBottomItem>) {
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int = list.size
}