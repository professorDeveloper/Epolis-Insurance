package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.data.local.shp.AppReference
import com.azamovhudstc.epolisinsurance.ui.adapter.BuyPolisAdapter
import com.azamovhudstc.epolisinsurance.utils.LocalData.clearDriverList
import com.azamovhudstc.epolisinsurance.utils.LocalData.isDonePosition
import com.azamovhudstc.epolisinsurance.utils.enums.LanguageType
import com.azamovhudstc.epolisinsurance.utils.setPositionListener
import com.shuhart.stepview.StepView
import com.shuhart.stepview.StepView.ANIMATION_ALL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*


@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var backPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initStepView(step_view)
        noPremium()


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            })
    }


    private fun noPremium() {
        toolbar.setNavigationOnClickListener {
            backOldScreen()
        }
        setPositionListener {
         changeNextScreen(it)
        }
        initFragmentContainer()
    }

    private fun initFragmentContainer(){
        val buyPolisAdapter = BuyPolisAdapter(requireActivity())
        fragmentContainer.setOnTouchListener(null);

        fragmentContainer.adapter = buyPolisAdapter
        fragmentContainer.isUserInputEnabled = false
        fragmentContainer.isUserInputEnabled = false

    }
    private fun initStepView(stepView: StepView) {

        stepView.state
            .steps(object : ArrayList<String?>() {
                init {
                    if (AppReference(requireContext()).currentLanguage == LanguageType.uz) {
                        add("Umumiy Malumot")
                        add("Haydovchi Malumoti")
                        add("Shartnoma Malumotlari")
                        add("To`lov qism")

                    } else {
                        add("Общая информация")
                        add("Инфомация Водителя")
                        add("Информация о контракте")
                        add("Оплата")
                    }
                }
            })
            // In case you specify both steps array is chosen.
            .stepsNumber(0)
            .animationType(ANIMATION_ALL)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()
    }
    private fun changeNextScreen(it: Int){
        backPosition=it
        if (!isDonePosition(it+1)) {
            step_view.animate().start()
            step_view.done(false)
            step_view.go(it, true)

        }
        fragmentContainer.setCurrentItem(it, true)
    }
    private fun backOldScreen(){
        if (backPosition==0){
            findNavController().popBackStack()
        }
        else{
            println("backPosition:$backPosition")
            fragmentContainer.setCurrentItem(backPosition-1,true)
            backPosition--

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        clearDriverList()
    }

}