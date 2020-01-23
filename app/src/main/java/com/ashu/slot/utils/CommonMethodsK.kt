package com.ashu.slot.utils

import java.text.ParseException
import java.text.SimpleDateFormat

object CommonMethodsK {

    @Throws(ParseException::class)
    fun convertDateToDay(date: String?): String {
        val dateNumber = date?.substring(date.lastIndexOf("-") + 1)
        val inFormat = SimpleDateFormat("dd-MM-yyyy")
        val dateConverted = inFormat.parse(date)
        val outFormat = SimpleDateFormat("EEEE")
        val day = outFormat.format(dateConverted)
        return dateNumber + "\n" + day.substring(0, 3)
    }

    fun getMonth(date: String): String {
        val monthNumber =
            date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"))
        return when (monthNumber.toInt()) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> ""
        }
    }
}