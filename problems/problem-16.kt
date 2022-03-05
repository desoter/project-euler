package problems

import java.util.*

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 *
 * What is the sum of the digits of the number 2^1000?
 */
fun main() {

    val nParallel = 1

    println("${Date()} - Start with $nParallel parallel processes")

    val result = calculatePowerOfTwo(1000)
    val sumOfDigits = result.sumOf { it.digitToInt().toLong() }

    println("${Date()} - Result: $result; sumOfDigits: $sumOfDigits")

    // Less fun way; probably at the time the problem was conceived this option didn't exist
    //val result2 = BigDecimal(2).pow(1000).toString().sumOf { it.digitToInt().toLong() }
    //println("${Date()} - Result: $result2")
}

fun calculatePowerOfTwo(exp: Int) : String {

    val numberAsString = StringBuilder("2")

    var tempNumberAsString : String
    var remainder : Short = 0
    var tempResult : Int

    for (e in 2..exp) {

        tempNumberAsString = numberAsString.toString()
        remainder = 0

        numberAsString.clear()

        tempNumberAsString.map { digit ->

            tempResult = digit.digitToInt() * 2 + remainder

            if (tempResult > 9) {

                remainder = 1
                numberAsString.append(tempResult - 10)

            } else {

                remainder = 0
                numberAsString.append(tempResult)
            }
        }

        if (remainder > 0) numberAsString.append(remainder)

    }

    return numberAsString.toString().reversed()
}