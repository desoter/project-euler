package problems

import kotlin.math.pow

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
fun main() {

    val result=mcm(1..20)
    println("Result: $result")
}

fun mcm(numbersRange: IntRange) : Int {

    // Identify prime numbers less or equal to range max value
    val primeSet = sortedSetOf<Int>()
    for (n in numbersRange) {
        if (isPrime(n.toLong())) primeSet.add(n)
    }

    // Store the partial result in the form of <primeNumber, primeNumber^numberOfItsOccurInN>
    val primesExp = sortedMapOf<Int, Int>()
    for (n in numbersRange) {

        var tempResult = n

        for (p in primeSet) {

            if (p > n) break

            var occurrences = 0
            while (tempResult.mod(p)==0) { // While it can be dived by p
                occurrences++

                tempResult /= p
            }
            val exp = p.toDouble().pow(occurrences).toInt()

            // Keep only the maximum power of a given prime number p
            if (exp > primesExp[p] ?: 0) primesExp[p]=exp

        }

    }

    var mcm = 1

    for (v in primesExp.values) mcm *= v

    return mcm
}