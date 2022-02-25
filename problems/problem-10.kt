package problems

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 *
 * Find the sum of all the primes below two million.
 */
fun main() {

    val result=sumOfPrimesBelow(2_000_000)

    println("Result: $result")
}

fun sumOfPrimesBelow(ceiling: Int) : Long {

    var sum = 0L

    for (n in 1L until ceiling) {

        if (isPrime(n)) sum += n
    }

    return sum
}
