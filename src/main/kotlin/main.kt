package ru.netology

const val JUST_NOW = "только что" //Если количество секунд от 0 до 60, работает вариант с только что.
const val AGO_MINUTES = "минут назад"//Если количество секунд от 61 до 60 * 60 (один час), работает вариант с x минут назад.
const val AGO_HOURS = "часов назад"//Если количество секунд от 60 * 60 + 1 до 24 * 60 * 60 (сутки) и , работает вариант с x часов назад.
const val TODAY = "сегодня" //Если количество секунд от суток до двух, то - сегодня.
const val YESTERDAY = "вчера" //Если количество секунд от двух суток до трёх, то - вчера.
const val LONG_TIME = "давно" //Если количество секунд больше трёх суток, то - давно.


fun main() {
    print("Сколько секунд назад был в сети пользователь? Напишите: ")
    val seconds = readLine()?. toInt() ?: return
    println("Пользователь был ${calculateTime(agoToText(seconds), seconds)} ${ago(agoToText(seconds), seconds)} ")
}

fun agoToText(seconds: Int) = when {
    seconds <= 60 -> JUST_NOW
    (seconds > 60 && seconds <= 60 * 60) -> AGO_MINUTES
    (seconds > 60 * 60 && seconds <= 24 * 60 * 60) -> AGO_HOURS
    (seconds > 24 * 60 * 60 && seconds <= 48 * 60 * 60) -> TODAY
    (seconds > 48 * 60 * 60 && seconds <= 72 * 60 * 60) -> YESTERDAY
    seconds > 72 * 60 * 60 -> LONG_TIME
    else -> "Что-то не задалось"
}

fun calculateTime (agoToText: String, seconds: Int) = when (agoToText) {
    AGO_MINUTES -> seconds / 60
    AGO_HOURS -> seconds / (60 * 60)
    else -> ""
}


fun ago (agoToText: String, seconds: Int) = when (agoToText) {
    JUST_NOW -> JUST_NOW
    AGO_MINUTES -> when ((seconds / 60) % 100) {
        2, 3, 4 -> "минуты назад"
        11 -> "минут назад"
        else -> "минут назад"
    }
    AGO_HOURS -> when ((seconds / (60 * 60)) % 100) {
        1 -> "час назад"
        2 -> "часа назад"
        11 -> "часов назад"
        else -> "часов назад"
    }
    TODAY -> TODAY
    YESTERDAY -> YESTERDAY
    else -> LONG_TIME
}