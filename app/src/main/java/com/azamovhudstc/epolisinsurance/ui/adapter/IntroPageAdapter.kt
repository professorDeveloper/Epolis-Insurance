package com.azamovhudstc.epolisinsurance.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.epolisinsurance.ui.screen.welcome.intro_page.PageOne
import com.azamovhudstc.epolisinsurance.ui.screen.welcome.intro_page.PageThird
import com.azamovhudstc.epolisinsurance.ui.screen.welcome.intro_page.PageTwo

class IntroPageAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{
                return PageOne()
            }
            1->{
                return PageTwo()
            }
            else->{
                return PageThird()
            }
        }
    }

}