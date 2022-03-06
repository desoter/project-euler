package problems

import java.util.*

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then
 * there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 *
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
 * how many letters would be used?
 *
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
 * contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and"
 * when writing out numbers is in compliance with British usage.
 */
fun main() {

    val nParallel = 1

    println("${Date()} - Start with $nParallel parallel processes")

    val result = countSumOfLetters(1..1000)

    println("${Date()} - Result: $result")

}

fun countSumOfLetters(numbers: IntRange) : Int {

    var sum = 0

    for (n in numbers) {

        sum += countLetters(n)

    }
    return sum
}

fun countLetters(number: Int) : Int {

    var numberInLetters = ""
    val numberString = number.toString()

    when(numberString.length) {

        1 -> numberInLetters = transformNumberUnitToWord(numberString[0])
        2 -> numberInLetters =
                if (number < 20) {
                    transformNumberTeenToWord(numberString) }
                else {
                    transformNumberTensToWord(numberString[0]) +
                            transformNumberUnitToWord(numberString[1]) }
        3 -> numberInLetters =
            when {
                numberString[1].digitToInt() * 10 + numberString[2].digitToInt() == 0 ->
                    transformNumberUnitToWord(numberString[0]) + "hundred"
                numberString[1].digitToInt() * 10 + numberString[2].digitToInt() < 10 ->
                    transformNumberUnitToWord(numberString[0]) + "hundred" + "and" +
                            transformNumberUnitToWord(numberString[2])
                numberString[1].digitToInt() * 10 + numberString[2].digitToInt() < 20 ->
                    transformNumberUnitToWord(numberString[0]) + "hundred" + "and" +
                            transformNumberTeenToWord(numberString[1].toString() + numberString[2].toString())
                else ->
                    transformNumberUnitToWord(numberString[0]) + "hundred" + "and" +
                            transformNumberTensToWord(numberString[1]) +
                            transformNumberUnitToWord(numberString[2])
            }
        4 -> numberInLetters = "onethousand"

    }

    println(numberInLetters)

    return numberInLetters.length
}

fun transformNumberUnitToWord(number: Char) : String =
    when(number) {
        '0' -> ""
        '1' -> "one"
        '2' -> "two"
        '3' -> "three"
        '4' -> "four"
        '5' -> "five"
        '6' -> "six"
        '7' -> "seven"
        '8' -> "eight"
        '9' -> "nine"
        else -> ""
    }

fun transformNumberTeenToWord(number: String) : String =
    when(number) {
        "10" -> "ten"
        "11" -> "eleven"
        "12" -> "twelve"
        "13" -> "thirteen"
        "14" -> "fourteen"
        "15" -> "fifteen"
        "16" -> "sixteen"
        "17" -> "seventeen"
        "18" -> "eighteen"
        "19" -> "nineteen"
        else -> ""
    }

fun transformNumberTensToWord(tens: Char) : String =
    when(tens) {
        '2' -> "twenty"
        '3' -> "thirty"
        '4' -> "forty"
        '5' -> "fifty"
        '6' -> "sixty"
        '7' -> "seventy"
        '8' -> "eighty"
        '9' -> "ninety"
        else -> ""
    }