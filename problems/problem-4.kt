package problems

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
fun main() {

    val largestPalindrome= largestPalindrome()
    println("Largest palindrome: $largestPalindrome")
}

fun largestPalindrome() : Int {

    var largest = 0

    for (i in 999 downTo 100) {

        for (j in 999 downTo 100) {

            val product = (i*j)
            val productString = product.toString()
            val productLength = productString.length

            for (k in 0..2) {

                if (productString[k] != productString[(productLength-1)-k]) break

                if (k==2) // Passed palindrome check above
                    if (product > largest)
                        largest = product
            }
        }
    }

    return largest
}