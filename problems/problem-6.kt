package problems

import kotlin.math.pow

/**
 * The sum of the squares of the first ten natural numbers is
 *     1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is
 *     (1+2+...+10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is
 *     3025-385 = 2640
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
fun main() {

    val result=squareOfSum(1..100) - sumOfSquares(1..100)
    println("Result: $result")
}

fun squareOfSum(numbersRange: IntRange) : Int {

    var sum = 0

    for (n in numbersRange) sum += n

    return sum.toFloat().pow(2).toInt()
}

fun sumOfSquares(numbersRange: IntRange) : Int {

    var sum = 0

    for (n in numbersRange) sum += n.toFloat().pow(2).toInt()

    return sum
}