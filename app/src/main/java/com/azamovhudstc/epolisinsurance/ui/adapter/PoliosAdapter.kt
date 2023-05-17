package com.azamovhudstc.epolisinsurance.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.PolsItem
import kotlinx.android.synthetic.main.polis_item.view.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class PoliosAdapter :
    androidx.recyclerview.widget.ListAdapter<PolsItem, PoliosAdapter.PolsViewHolder>(
        PolsItemCallback
    ) {

    inner class PolsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("UseCompatLoadingForDrawables", "NewApi")
        fun onBind(data: PolsItem) {
            itemView.apply {
                val startDate = data.date
                val endDate = data.endDate
                val currentDate = LocalDate.now()
                val days =
                    ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate))
                play_timer_progress.max = days.toInt()
                val currentProgress =
                    ChronoUnit.DAYS.between(LocalDate.parse(startDate), currentDate)

                play_timer_progress.progress = currentProgress.toInt()
                item_id.text=data.policyNumber
                date_item.text=data.date
                textView11.text=data.endDate
                when(data.polisType){
                    "IMPEX" -> {
                        play_timer_progress.progressDrawable =context.getDrawable(R.drawable.progress_orange)
                    }
                    "KACKO" -> {
                        play_timer_progress.progressDrawable =context.getDrawable(R.drawable.progress_green)
                    }
                    "OCAGO" -> {
                        play_timer_progress.progressDrawable =context.getDrawable(R.drawable.progress_yellow)
                    }
                    else ->{
                        play_timer_progress.progressDrawable =context.getDrawable(R.drawable.progress_blue)

                    }
                }
            }

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