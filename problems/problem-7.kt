package problems

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10001st prime number?
 */
fun main() {

    val result=findPrime(10001)
    println("Result: $result")
}

fun findPrime(targetPosition: Int) : Long {

    var currentPosition=2 // Already count 2 and 3 as the first prime numbers
    var primeAtPosition=-1L

    for (n in 5..1_000_000L) { // The max of the range is arbitrary

        if (isPrime(n)) {
            currentPosition++

            if (currentPosition == targetPosition) {
                primeAtPosition = n
                break
            }
        }
    }

    return primeAtPosition
}

