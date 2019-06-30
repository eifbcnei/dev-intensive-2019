package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
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
            val pair = Utils.parseFullName(fullName)
            var arg1 = pair.first
            var arg2 = pair.second
            return User(id = "$lastId", firstName = arg1, lastName = arg2)
        }
    }

    class Builder{
        var id: String?=null
        var firstName: String?=null
        var lastName: String?=null
        var avatar: String?=null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = null
        var isOnline: Boolean = false
        fun id(value:String?) : Builder{
            id=value
            return this
        }
        fun firstName(value : String?):Builder{
            firstName=value
            return this
        }
        fun lastName(value : String?):Builder{
            lastName=value
            return this
        }
        fun avatar(value: String):Builder {
            avatar = value
            return this
        }

        fun rating(value: Int):Builder {
            rating = value
            return this
        }

        fun respect(value: Int):Builder {
            respect = value
            return this
        }

        fun lastVisit(value: Date):Builder {
            lastVisit = value
            return this
        }

        fun isOnline(value: Boolean):Builder{
            isOnline = value
            return this
        }

        fun build() = User(
            id ?: (++lastId).toString(),
            firstName,
            lastName,
            avatar,
            rating,
            respect,
            lastVisit,
            isOnline
        )
    }
}