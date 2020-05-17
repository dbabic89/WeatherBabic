package com.example.weatherbabic.utils

import java.text.SimpleDateFormat
import java.util.*

const val DAY_NAME_MONTH_DAY_PATTERN = "EEE, MMM d"
const val HOUR_MINUTE_PATTERN = "H:mm"

fun getDate(unixTimeStamp: Long): String {
    return getTextForUnixTimeStamp(unixTimeStamp, DAY_NAME_MONTH_DAY_PATTERN)
}

fun getTime(unixTimeStamp: Long): String {
    return getTextForUnixTimeStamp(unixTimeStamp, HOUR_MINUTE_PATTERN)
}

private fun getTextForUnixTimeStamp(unixTimeStamp: Long, pattern: String): String {
    val javaTimeStamp = convertUnixToJavaTimeStamp(unixTimeStamp)
    val date = Date(javaTimeStamp)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
    return simpleDateFormat.format(date)
}

private fun convertUnixToJavaTimeStamp(unixTimeStamp: Long) = unixTimeStamp * 1000L