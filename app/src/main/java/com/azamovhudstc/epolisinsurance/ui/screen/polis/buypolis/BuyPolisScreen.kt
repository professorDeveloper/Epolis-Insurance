package com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.epolisinsurance.R
import com.azamovhudstc.epolisinsurance.app.App
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.AllInfoPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoContractPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.InfoDriversPage
import com.azamovhudstc.epolisinsurance.ui.screen.polis.buypolis.pages.SendMoneyPage
import com.azamovhudstc.epolisinsurance.utils.setPositionListener
import com.shuhart.stepview.StepView
import com.shuhart.stepview.StepView.ANIMATION_ALL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_polis_screen.*


@AndroidEntryPoint
class BuyPolisScreen : Fragment(R.layout.fragment_buy_polis_screen) {
    private var backPosition=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initStepView(step_view)
        noPremium()
    }

    private fun noPremium(){
        val fragment= AllInfoPage()
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.from_right,R.anim.to_left,R.anim.from_left,R.anim.to_right)
        transaction.add(R.id.fragmentContainer, fragment)
            .addToBackStack("infoPage")

        transaction.commit()
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()

        }
        setPositionListener{
            backPosition=it
            when (it) {
                1 -> {
                    step_view.animate().start()
                    step_view.go(it, true)
                    val transactionDriver = fragmentManager.beginTransaction()
                    transactionDriver.setCustomAnimations(R.anim.from_right,R.anim.to_left,R.anim.from_left,R.anim.to_right)

                    var infoDriver= InfoDriversPage()
                    transactionDriver.replace(R.id.fragmentContainer, infoDriver)
                        .addToBackStack("infoDriverPage")
                    transactionDriver.commit()

                }
                2 -> {
                    step_view.animate().start()
                    step_view.go(it, true)
                    val transactionDriver = fragmentManager.beginTransaction()
                    transactionDriver.setCustomAnimations(R.anim.from_right,R.anim.to_left,R.anim.from_left,R.anim.to_right)

                    var infoContractPage=InfoContractPage()
                    transactionDriver.replace(R.id.fragmentContainer, infoContractPage)
                        .addToBackStack("infoContractPage")
                    transactionDriver.commit()

                }
                else -> {
                    step_view.animate().start()
                    step_view.go(it, true)
                    val transactionDriver = fragmentManager.beginTransaction()
                    transactionDriver.setCustomAnimations(R.anim.from_right,R.anim.to_left,R.anim.from_left,R.anim.to_right)

                    var sendMoneyPage=SendMoneyPage()
                    transactionDriver.replace(R.id.fragmentContainer, sendMoneyPage)
                        .addToBackStack("sendMoneyPage")
                    transactionDriver.commit()

                }
            }

        }

    }
    private fun initStepView(stepView: StepView) {

        stepView.state
            .steps(object : ArrayList<String?>() {
                init {
                    add(App.instance.resources.getString(R.string.step_one))
                    add(App.instance.resources.getString(R.string.step_two))
                    add(App.instance.resources.getString(R.string.step_third))
                    add(App.instance.resources.getString(R.string.step_four))
                }
            })
            // In case you specify both steps array is chosen.
            .stepsNumber(2)
            .animationType(ANIMATION_ALL)
            .animationDuration(resources.getInteger(android.R.integer.config_shortAnimTime))
            .commit()
    }

}