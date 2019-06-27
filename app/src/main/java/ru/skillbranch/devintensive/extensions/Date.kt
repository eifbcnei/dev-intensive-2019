package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}

const val SECONDS = 1000L
const val MINUTES = 60 * SECONDS
const val HOURS = MINUTES * 60
const val DAYS = 24 * HOURS

fun Date.add(value: Int, unit: TimeUnits): Date {
    var time = this.time

    time += when (unit) {
        TimeUnits.SECOND -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTES
        TimeUnits.HOUR -> value * HOURS
        TimeUnits.DAY -> value * DAYS
    }
    this.time = time
    return this
}