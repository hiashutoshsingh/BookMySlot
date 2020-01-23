package com.ashu.slot.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashu.slot.*
import com.ashu.slot.model.Slot
import com.ashu.slot.repository.RepositoryFactory
import com.ashu.slot.utils.CommonMethods
import com.ashu.slot.utils.CommonMethodsK
import com.ashu.slot.view.adapters.ViewPagerAdapter
import com.ashu.slot.viewModel.SlotViewModel
import com.ashu.slot.viewModel.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: SlotViewModel
    private val slotList = arrayListOf<Slot>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel =
            ViewModelProviders.of(
                this,
                ViewModelFactory(RepositoryFactory.createSlotRepository())
            ).get(SlotViewModel::class.java)
        mainActivityViewModel.getSlots()
        mainActivityViewModel.githubAccount.observe(this, Observer {
            slotList.addAll(it)
            setUpViewPager(slotList)
        })

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpViewPager(slotList: ArrayList<Slot>) {

        viewPager.adapter =
            ViewPagerAdapter(this, slotList)
        TabLayoutMediator(tabs, viewPager,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                tab.text = "${CommonMethodsK.convertDateToDay(
                    CommonMethods.getDates(
                        CommonMethods.groupDates(slotList)
                    )[position]
                )}"
            }).attach()


        txtMonth.text = CommonMethodsK.getMonth(slotList[0].startTime)
    }


}
