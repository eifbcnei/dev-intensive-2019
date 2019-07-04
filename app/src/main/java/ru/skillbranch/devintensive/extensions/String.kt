package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    trim()
    var result = StringBuilder()
    var curIndex = 0
    while (curIndex < this.length && curIndex < length) {
        result.append(this[curIndex])
        curIndex++
    }
    while (result.endsWith(" ")) {
        result = StringBuilder(result.removeSuffix(" "))
    }
    if (result.length >= length - 1) result.append("...")
    return result.toString()
}
