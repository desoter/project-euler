package problems

import kotlin.math.min

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
fun main() {

    val largestPalindrome= largestPalindrome(3)
    println("Largest palindrome: $largestPalindrome")
}

fun largestPalindrome(numbersDigits: Int) : Int {

    val minNumberStringBuilder: StringBuilder = java.lang.StringBuilder("1")
    val maxNumberStringBuilder: StringBuilder = java.lang.StringBuilder("9")

    for (i in 1 until numbersDigits) {
        minNumberStringBuilder.append("0")
        maxNumberStringBuilder.append("9")
    }
    val minNumber = minNumberStringBuilder.toString().toInt()
    val maxNumber = maxNumberStringBuilder.toString().toInt()

    var largest = 0

    for (i in maxNumber downTo minNumber) {

        for (j in maxNumber downTo minNumber) {

            val product = (i*j)
            val productString = product.toString()
            val productLength = productString.length

            for (k in 0 until numbersDigits) {

                if (productString[k] != productString[(productLength-1)-k]) break

                if (k==(numbersDigits-1)) // Passed palindrome check above
                    if (product > largest)
                        largest = product
            }
        }
    }

    return largest
}