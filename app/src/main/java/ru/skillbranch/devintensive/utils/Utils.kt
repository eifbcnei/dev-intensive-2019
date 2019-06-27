package ru.skillbranch.devintensive.utils

fun parseFullName(fullName: String?): Pair<String, String> {
    if (fullName == null) return Pair("null", "null")

    if (!fullName.contains(" ")) return Pair(fullName, "null")

    if (fullName == " ") return Pair("null", "null")

    var args: List<String> = fullName?.split(" ")
    var arg1 = args.getOrNull(0)
    var arg2 = args.getOrNull(1)
    return Pair(arg1!!, arg2!!)
}