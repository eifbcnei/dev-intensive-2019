package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) :
            this(id, firstName, lastName, null)

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            var args: List<String>? = fullName?.split(" ")
            var arg1 = args?.getOrNull(0)
            var arg2 = args?.getOrNull(1)
            return User(id = "$lastId", firstName = arg1, lastName = arg2)
        }
    }
}