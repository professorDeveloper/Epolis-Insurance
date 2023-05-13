package com.azamovhudstc.epolisinsurance.ui.screen.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.utils.slideUp
import kotlinx.android.synthetic.main.langauge_screen.view.*

class LanguageScreen : Fragment(R.layout.langauge_screen) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.next_btn_language.slideUp(1000,0)
    view.next_btn_language.setOnClickListener {
      findNavController().navigate(R.id.mainScreen)
    }
  }
}