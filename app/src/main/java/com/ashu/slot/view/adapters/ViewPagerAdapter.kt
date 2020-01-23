package com.ashu.slot.view.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ashu.slot.model.Slot
import com.ashu.slot.utils.CommonMethods
import com.ashu.slot.view.SlotFragment


class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val slotList: ArrayList<Slot>
) :
    FragmentStateAdapter(fragmentActivity) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getItemCount(): Int {
        return CommonMethods.groupDates(slotList).size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun createFragment(position: Int): Fragment {
        val date = CommonMethods.getDates(
            CommonMethods.groupDates(slotList)
        )[position]
        val slots = CommonMethods.getSlotPerDate(CommonMethods.groupDates(slotList), date)
        return SlotFragment(slots)
    }
}