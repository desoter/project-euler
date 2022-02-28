package problems

import kotlinx.coroutines.*
import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * The following iterative sequence is defined for the set of positive integers:
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 *
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 *
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 *
 * Which starting number, under one million, produces the longest chain?
 *
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
fun main() {

    val nParallel = 1
        //if (Runtime.getRuntime().availableProcessors()<=1) 1
        //else Runtime.getRuntime().availableProcessors()/2

    println("${Date()} - Start with $nParallel parallel processes")

    val result = runBlocking(Dispatchers.Default) {
        longestCollatzSequence(this, nParallel, 999_999)
    }

    println("${Date()} - Result: $result")
}

suspend fun longestCollatzSequence(
    coroutineScope: CoroutineScope,
    nParallel: Int,
    n: Long
) : Long {

    val jobs = arrayListOf<Job>()

    for (c in 0 until nParallel) {
        jobs.add(longestCollatzSequenceAsync(coroutineScope, n, nParallel, c))
    }

    jobs.joinAll()

    return 0
}

fun longestCollatzSequenceAsync(coroutineScope: CoroutineScope,
                               number: Long,
                               numbersStep: Int,
                               parallelId: Int) =
    coroutineScope.launch {

        var n=number-parallelId

        var maxSeqLength=1
        var startingNumberOfMaxLengthSeq=0L

        var currentLength : Int
        var currentSeqNumber : Long

        while (n > 0) {

            currentLength=1
            currentSeqNumber=n

            while (currentSeqNumber != 1L) {

                currentSeqNumber =
                    if (currentSeqNumber.mod(2) == 0) currentSeqNumber/2
                    else (3*currentSeqNumber)+1

                currentLength++
            }

            if (currentLength > maxSeqLength) {
                maxSeqLength=currentLength
                startingNumberOfMaxLengthSeq=n
            }

            n -= numbersStep
        }

        println("${Date()} - starting number $startingNumberOfMaxLengthSeq has the longest sequence of $maxSeqLength")

}