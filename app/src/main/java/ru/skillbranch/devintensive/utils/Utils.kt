package ru.skillbranch.devintensive.utils


object Utils {

    private val translitarationMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z",
        'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya",

        'А' to "A", 'Б' to "B", 'В' to "V", 'П' to "G", 'Д' to "D", 'Е' to "E", 'Ё' to "E", 'Ж' to "Zh", 'З' to "Z",
        'И' to "I", 'Й' to "I", 'К' to "K", 'Л' to "L", 'М' to "M", 'Н' to "N", 'О' to "O", 'П' to "P", 'Р' to "R",
        'С' to "S", 'Т' to "T", 'У' to "U", 'Ф' to "F", 'Х' to "H", 'Ц' to "C", 'Ч' to "Ch", 'Ш' to "Sh", 'Щ' to "Sh",
        'Ы' to "I", 'Э' to "E", 'Ю' to "Yu", 'Я' to "Ya"


    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName.isNullOrBlank()) return Pair(null, null)

        if (!fullName.contains(" ")) return Pair(fullName, null)

        if (fullName == " ") return Pair(null, null)


        var args: List<String> = fullName?.split(" ")
        var arg1 = args.getOrNull(0)
        var arg2 = args.getOrNull(1)
        return Pair(arg1!!, arg2!!)
    }

    private fun String.replaceAll(oldValue: String, newValue: String): String {
        var result = this
        while (result.contains(oldValue)) {
            result = result.replace(oldValue, newValue)
        }
        return result
    }

    private fun List<String>.notEmptyOrNullAt(index: Int) = getOrNull(index).let {
        if ("" == it) null
        else it
    }

    fun transliteration(payload: String, divider: String = " ") = buildString {
        payload.forEach {
            append(
                when {
                    it == ' ' -> divider
                    it.isUpperCase() -> translitarationMap[it.toLowerCase()]?.capitalize() ?: it.toString()
                    else -> translitarationMap[it] ?: it.toString()
                }
            )
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? = when {
        firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
        !firstName.isNullOrBlank() && lastName.isNullOrBlank() -> firstName[0].toUpperCase().toString()
        firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> lastName[0].toUpperCase().toString()
        !firstName.isNullOrBlank() && !lastName.isNullOrBlank() -> firstName[0].toUpperCase() + lastName[0].toUpperCase().toString()
        else -> throw IllegalStateException("Incorrect state in 'when' expression")
    }
}