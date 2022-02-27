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

    val possiblePrimeFactors = Array(nParallel) { 0L }

    val isPrimeFactorToBe = Array(nParallel) { coroutineScope.async { false } }

    var isPrimeFactor: Boolean

    for (i in (n/2) downTo 1 step nParallel.toLong()) {

        for (c in 0 until nParallel) {

            possiblePrimeFactors[c] = i-c

            isPrimeFactorToBe[c] = isPrimeFactorAsync(coroutineScope, n, possiblePrimeFactors[c])

        }

        isPrimeFactorToBe.forEachIndexed { c, it ->

            isPrimeFactor = it.await()

            if (isPrimeFactor) return possiblePrimeFactors[c]

        }
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

fun isPrimeFactorAsync(coroutineScope: CoroutineScope, number: Long, possiblePrimeFactor: Long) :
        Deferred<Boolean> = coroutineScope.async {

    when {
        possiblePrimeFactor <= 1 -> return@async false
        possiblePrimeFactor == 2L -> return@async true
        possiblePrimeFactor == 3L -> return@async true
        possiblePrimeFactor.mod(2) == 0 -> return@async false
        possiblePrimeFactor.mod(3) == 0 -> return@async false
        else -> {

            if (number.mod(possiblePrimeFactor) == 0L) {

                for (i in 5L..(floor(sqrt(possiblePrimeFactor.toDouble())).toLong()) step 6L) {

                    if (possiblePrimeFactor.mod(i) == 0L) return@async false
                    else if (possiblePrimeFactor.mod(i + 2) == 0L) return@async false

                }

            } else return@async false

            return@async true
        }
    }

}