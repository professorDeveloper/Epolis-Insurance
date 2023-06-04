package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.model.TabModel
import com.azamovhudstc.epolisinsurance.utils.LocalData
import com.azamovhudstc.epolisinsurance.utils.invisible
import kotlinx.android.synthetic.main.fragment_driver_page_item.*

class DriverPageItem : Fragment(R.layout.fragment_driver_page_item) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data=arguments?.getSerializable("dataDriver") as TabModel
        driver_id_title.text="${data.name}-Водитель"
        if (data.name.toInt()==1){
            delete_item.invisible()
        }

        delete_item.setOnClickListener {
            LocalData.setDriverCountListener.invoke(data.name.toInt())
        }

    }
}//