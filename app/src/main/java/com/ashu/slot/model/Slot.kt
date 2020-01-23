package com.ashu.slot.model


import com.google.gson.annotations.SerializedName

data class Slot(
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("is_booked")
    val isBooked: Boolean,
    @SerializedName("is_expired")
    val isExpired: Boolean,
    @SerializedName("slot_id")
    val slotId: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("username")
    val username: Any
)