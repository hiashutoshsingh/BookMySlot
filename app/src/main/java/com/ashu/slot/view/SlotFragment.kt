package com.ashu.slot.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashu.slot.R
import com.ashu.slot.model.Slot
import com.ashu.slot.utils.CommonMethods
import com.ashu.slot.view.adapters.SlotAdapter
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager
import java.util.*

class SlotFragment(private val slots: List<Slot>) : Fragment() {

    private val shift: MutableList<String>
    private lateinit var recyclerView: RecyclerView
    private var adapter: SlotAdapter? = null
    @RequiresApi(api = Build.VERSION_CODES.N)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_slot, container, false)
        shift.clear()
        shift.addAll(CommonMethods.getShifts(slots))
        return view
    }

    init {
        shift = ArrayList()
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvSLots)
        val expMgr = RecyclerViewExpandableItemManager(null)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = context?.let { SlotAdapter(it, shift, CommonMethods.groupByShift(slots)) }
        recyclerView.adapter = expMgr.createWrappedAdapter(adapter!!)
        expMgr.attachRecyclerView(recyclerView)
    }

}