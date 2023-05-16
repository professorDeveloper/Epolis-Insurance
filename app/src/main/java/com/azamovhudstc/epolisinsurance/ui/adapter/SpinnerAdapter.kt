package com.azamovhudstc.epolisinsurance.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.item_spinner.view.*

class SpinnerAdapter : BaseAdapter() {

    private var data: ArrayList<String>? = null

    fun setAdapter(data: ArrayList<String>) {
        this.data = data
    }

    override fun getCount(): Int = data!!.size

    override fun getItem(p0: Int): String {
        return data!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(p2?.context).inflate(R.layout.item_spinner, p2, false)

        view.tv_spinner.text = data!![p0]



        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }
}