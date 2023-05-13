package com.azamovhudstc.epolisinsurance.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryVh>() {
    private val list = ArrayList<CategoryItem>()
    private var checkedPosition = 0
    private lateinit var itemClickListener: ((CategoryItem) -> Unit)

    inner class CategoryVh(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: CategoryItem) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVh {
        return CategoryVh(
            LayoutInflater.from(parent.context).inflate(R.layout.home_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryVh, position: Int) {
        holder.onBind(list[position])
    }

    fun submitList(newList: List<CategoryItem>) {
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int = list.size
}