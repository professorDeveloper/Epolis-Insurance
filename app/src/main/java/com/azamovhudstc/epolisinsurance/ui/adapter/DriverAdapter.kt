package com.azamovhudstc.epolisinsurance.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.Driver
import kotlinx.android.synthetic.main.drive_item.view.*

class DriverAdapter : ListAdapter<Driver, DriverAdapter.ActorsWh>(ActorsCallback) {

    private lateinit var clickItemListener :((Driver)->Unit)

    fun setItemClickListener(listener: ((Driver) -> Unit)) {
        clickItemListener = listener
    }

    inner class ActorsWh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(data: Driver) {
            var  sendAPI=false
            itemView.apply {
                if (sendAPI){
                    println("Send API.......")
                }
                driver_id_title.text="${data.driverId}-Водитель"
                val textWatcher = object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                    override fun afterTextChanged(s: Editable?) {
                        val input1 = driver_passSere.text.toString().trim()
                        val input2 = driver_passNumber.text.toString().trim()
                        val input3 = driver_bornDate.text.toString().trim()

                        sendAPI = input1.isNotEmpty() && input2.isNotEmpty() && input3.isNotEmpty()
                    }
                }
                driver_passSere.addTextChangedListener(textWatcher)
                driver_passNumber.addTextChangedListener(textWatcher)
                driver_bornDate.addTextChangedListener(textWatcher)


            }
        }
    }

    object ActorsCallback : DiffUtil.ItemCallback<Driver>() {
        override fun areItemsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem.driverId == newItem.driverLicenseDateNumber
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsWh {
        return ActorsWh(
            LayoutInflater.from(parent.context).inflate(R.layout.drive_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorsWh, position: Int) {
        holder.onBind(getItem(position))
    }
}