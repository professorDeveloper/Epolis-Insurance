package com.azamovhudstc.epolisinsurance.ui.screen.polis.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import kotlinx.android.synthetic.main.fragment_all_page.view.*

class AllPage : Fragment(R.layout.fragment_all_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.add_polis.setOnClickListener {
            findNavController().navigate(R.id.addPolis)
        }

    }
}