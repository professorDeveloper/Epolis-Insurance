package com.azamovhudstc.epolisinsurance.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.CategoryItem
import kotlinx.android.synthetic.main.home_category_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryVh>() {
    private val list = ArrayList<CategoryItem>()
    private var checkedPosition = 0
    private lateinit var itemClickListener: ((CategoryItem) -> Unit)
    fun setItemClickListener(listener:(CategoryItem)->Unit){
        itemClickListener=listener
    }

    inner class CategoryVh(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: CategoryItem) {
            itemView.apply {
                category_title.setText(data.title)
                if (checkedPosition == -1) {
                    container.setBackgroundResource(R.drawable.un_selected_category_bg)
                    category_title.setTextColor(Color.BLACK)
                } else {
                    if (checkedPosition == adapterPosition) {
                        container.setBackgroundResource(R.drawable.selected_category_bg)
                        category_title.setTextColor(Color.WHITE)
                    } else {
                        container.setBackgroundResource(R.drawable.un_selected_category_bg)
                        category_title.setTextColor(Color.BLACK)
                    }
                }

                itemView.setOnClickListener {
                    itemView.apply {
                        container.setBackgroundResource(R.drawable.selected_category_bg)
                        category_title.setTextColor(Color.WHITE)
//                        category_title.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
//                        category_title.layoutParams.height = 30.dpToPx
                        if (checkedPosition != adapterPosition) {
                            notifyItemChanged(checkedPosition)
                            checkedPosition = adapterPosition
                            itemClickListener.invoke(data)
                        }else{
                            itemClickListener.invoke(data)
                        }
                    }
                }
            }
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

    fun submitList(newList: ArrayList<CategoryItem>) {
        list.clear()
        list.addAll(newList)
    }

    override fun getItemCount(): Int = list.size
}