package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    fun askQuestion() = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        //пара из значений успешности теста и комментарий в случае ошибки
        val (isValid, comment) = question.testInput(answer)
        return if (isValid) {
            if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился!\n${askQuestion()}" to status.color
            } else {
                status = status.nextStatus()
                // 3 попытки, иначе - заново
                if (status == Status.NORMAL) {
                    question = Question.NAME
                }
                "Это неправильный ответ!\n${askQuestion()}" to status.color
            }
        } else {
            "$comment\n${askQuestion()}" to status.color
        }
    }


    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun testInput(answer: String): Pair<Boolean, String> =
                answer[0].isUpperCase() to "Имя должно начинаться с заглавной буквы"

            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun testInput(answer: String): Pair<Boolean, String> =
                !answer[0].isUpperCase() to "Профессия должна начинаться со строчной буквы"

            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun testInput(answer: String): Pair<Boolean, String> =
                !answer.any { it.isDigit() } to "Материал не должен содержать цифр"

            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun testInput(answer: String): Pair<Boolean, String> =
                !answer.any { !it.isDigit() } to "Год моего рождения должен содержать только цифры"

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun testInput(answer: String): Pair<Boolean, String> =
                (answer.length == 7 && !answer.any { !it.isDigit() }) to "Серийный номер содержит только цифры, и их 7"

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun testInput(answer: String): Pair<Boolean, String> = true to ""//игнорируем валидацию

            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
        abstract fun testInput(answer: String): Pair<Boolean, String>
    }
}