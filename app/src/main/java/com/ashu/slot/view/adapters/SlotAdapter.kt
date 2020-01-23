package com.ashu.slot.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ashu.slot.R
import com.ashu.slot.model.Slot
import com.ashu.slot.utils.CommonMethods
import com.ashu.slot.view.adapters.SlotAdapter.ChildHolder
import com.ashu.slot.view.adapters.SlotAdapter.ParentHolder
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder
import java.text.ParseException

class SlotAdapter(
    private val context: Context,
    private val shiftList: List<String>,
    private val groupByShift: Map<String, List<Slot>>
) : AbstractExpandableItemAdapter<ParentHolder, ChildHolder>() {
    override fun getGroupCount(): Int {
        return shiftList.size
    }

    override fun getChildCount(groupPosition: Int): Int {
        return CommonMethods.getSlotsPerShift(groupByShift, shiftList[groupPosition]).size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ParentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_slot_item, parent, false)
        return ParentHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_slot_time_item, parent, false)
        return ChildHolder(view)
    }

    override fun onBindGroupViewHolder(
        holder: ParentHolder,
        groupPosition: Int,
        viewType: Int
    ) {
        holder.titleText.text = shiftList[groupPosition]
        holder.availableSlotText.text = CommonMethods.getSlotsPerShift(
            groupByShift,
            shiftList[groupPosition]
        ).size.toString() + " Slots available"
    }

    override fun onBindChildViewHolder(
        holder: ChildHolder,
        groupPosition: Int,
        childPosition: Int,
        viewType: Int
    ) {
        val (endTime, isBooked, isExpired, _, startTime) = CommonMethods.getSlotsPerShift(
            groupByShift,
            shiftList[groupPosition]
        )[childPosition]
        try {
            holder.startTime.text = CommonMethods.getTime(startTime)
            holder.endTime.text = CommonMethods.getTime(endTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (isExpired || isBooked) holder.childlayout.setBackgroundColor(
            context.resources.getColor(
                R.color.grey
            )
        )
    }

    override fun onCheckCanExpandOrCollapseGroup(
        holder: ParentHolder,
        groupPosition: Int,
        x: Int,
        y: Int,
        expand: Boolean
    ): Boolean {
        if (expand) {
            holder.upDownArrow.setImageDrawable(context.resources.getDrawable(R.drawable.ic_red_reveallist))
            holder.upDownArrow.rotation = 0f
        } else {
            holder.upDownArrow.setImageDrawable(context.resources.getDrawable(R.drawable.ic_red_reveallist))
            holder.upDownArrow.rotation = 180f
        }
        return true
    }

    class ParentHolder(v: View) : BaseViewHolder(v) {
        var titleText: TextView = v.findViewById(R.id.parent_text)
        var availableSlotText: TextView = v.findViewById(R.id.parent_slot_available)
        var upDownArrow: ImageView = v.findViewById(R.id.parent_up_down_arrow)

    }

    class ChildHolder(v: View) : BaseViewHolder(v) {
        var startTime: TextView = v.findViewById(R.id.child_start_time)
        var endTime: TextView = v.findViewById(R.id.child_end_time)
        var childlayout: LinearLayout = v.findViewById(R.id.child_layout)

    }

    open class BaseViewHolder(itemView: View?) :
        AbstractExpandableItemViewHolder(itemView!!)

    init {
        setHasStableIds(true)
    }
}