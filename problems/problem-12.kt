package problems

import java.util.*

/**
 * The sequence of triangle numbers is generated by adding the natural numbers.
 * So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 *
 * Let us list the factors of the first seven triangle numbers:
1: 1
3: 1,3
6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28

 * We can see that 28 is the first triangle number to have over five divisors.
 *
 * What is the value of the first triangle number to have over five hundred divisors?
 */
fun main() {

    val result=firstTriangleNumberWithNDivisors(501)

    println("${Date()} - Result: $result")
}

fun firstTriangleNumberWithNDivisors(targetNumberOfDivisors: Int) : Int {

    var triangleNumber = 1 // First triangle number
    var nDivisors: Int
    var divisorStep: Int
    var maxNDivisors=0

    for (n in 2..1_000_000) { // The max of the range is arbitrary

        triangleNumber += n
        nDivisors=1 // Itself

        divisorStep =
            if (triangleNumber.mod(2) != 0) 2 // Odd numbers cannot have even divisors(?)
            else 1

        for (divisor in 1..(triangleNumber/2) step divisorStep) {

            if (triangleNumber.mod(divisor) == 0) {
                nDivisors++

                if (nDivisors >= targetNumberOfDivisors) {
                    println("${Date()} - Triangle number: $triangleNumber; # of divisors: $nDivisors")
                    return triangleNumber
                }
            }
        }

        if (nDivisors > maxNDivisors) {
            println("${Date()} - Triangle number: $triangleNumber; # of divisors: $nDivisors")

            maxNDivisors = nDivisors
        }

    }

    return 0
}