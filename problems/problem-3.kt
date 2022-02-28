package problems

import kotlinx.coroutines.*
import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */
fun main() {

    val nParallel =
        if (Runtime.getRuntime().availableProcessors()<=1) 1
        else Runtime.getRuntime().availableProcessors()/2

    println("${Date()} - Start with $nParallel parallel processes")

    val result = runBlocking(Dispatchers.Default) {
        maxPrimeFactor(this, nParallel, 600851475143)
    }

    println("${Date()} - Result: $result")
}

suspend fun maxPrimeFactor(
    coroutineScope: CoroutineScope,
    nParallel: Int,
    n: Long
) : Long {

    val jobs = arrayListOf<Job>()

    for (c in 0 until nParallel) {
        jobs.add(maxPrimeDivisorCustomAsync(coroutineScope, n, nParallel, c))
    }

    jobs.joinAll()

    return 0
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

fun maxPrimeDivisorCustomAsync(coroutineScope: CoroutineScope,
                               number: Long,
                               numbersStep: Int,
                               parallelId: Int) =
    coroutineScope.launch {

        var n=(number/2)-parallelId
        while (n > 0) {

            if (number.mod(n) == 0L) {

                var isPrime=true
                for (i in 5L..(floor(sqrt(n.toDouble())).toLong()) step 6L) {

                    if (n.mod(i) == 0L) isPrime=false
                    else if (n.mod(i + 2) == 0L) isPrime=false
                }

                if (isPrime) {
                    println("${Date()} - maxPrime divisor found by process #$parallelId: $n")
                    return@launch
                }
            }

            n -= numbersStep
        }
}