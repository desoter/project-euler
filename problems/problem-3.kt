package problems

import kotlin.math.floor
import kotlin.math.sqrt

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */
fun main() {

    val largestPrimeFactor = maxPrimeFactor(600851475143)

    println("Largest prime factor: $largestPrimeFactor")
}

fun maxPrimeFactor(n: Long) : Long {

    for (i in (n/2) downTo 1) {

        if (n.mod(i) == 0L && isPrime(i)) return i

    }

    return 1
}

fun isPrime(n: Long) : Boolean {

    when {
        n <= 1  -> return false
        n == 2L -> return true
        n == 3L -> return true
        n.mod(2)==0 -> return false
        n.mod(3)==0 -> return false
        else -> {

            for (i in 5L..(floor(sqrt(n.toDouble())).toLong()) step 6L) {

                if (n.mod(i) == 0L) return false
                else if (n.mod(i+2) == 0L) return false

            }

            return true
        }
    }
}