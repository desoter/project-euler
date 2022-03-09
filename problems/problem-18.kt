package problems

import java.lang.Integer.max
import java.util.*

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 * the maximum total from top to bottom is 23.

                                        3
                                       7 4
                                      2 4 6
                                     8 5 9 3
 * That is, 3 + 7 + 4 + 9 = 23.

 * Find the maximum total from top to bottom of the triangle below:

                                                75
                                              95  64
                                            17  47  82
                                          18  35  87  10
                                        20  04  82  47  65
                                      19  01  23  75  03  34
                                    88  02  77  73  07  63  67
                                  99  65  04  28  06  16  70  92
                                41  41  26  56  83  40  80  70  33
                              41  48  72  33  47  32  37  16  94  29
                            53  71  44  65  25  43  91  52  97  51  14
                          70  11  33  28  77  73  17  78  39  68  17  57
                        91  71  52  38  17  14  91  43  58  50  27  29  48
                      63  66  04  68  89  53  67  30  73  16  69  87  40  31
                    04  62  98  27  23  09  70  98  73  93  38  53  60  04  23

 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
 * However, Problem 67, is the same challenge with a triangle containing one-hundred rows;
 * it cannot be solved by brute force, and requires a clever method! ;o)
 */
fun main() {

    val nParallel = 1

    println("${Date()} - Start with $nParallel parallel processes")

    val result = maximumPathSum(
        treeNumbers =
            "75 " +
            "95 64 " +
            "17 47 82 " +
            "18 35 87 10 " +
            "20 04 82 47 65 " +
            "19 01 23 75 03 34 " +
            "88 02 77 73 07 63 67 " +
            "99 65 04 28 06 16 70 92 " +
            "41 41 26 56 83 40 80 70 33 " +
            "41 48 72 33 47 32 37 16 94 29 " +
            "53 71 44 65 25 43 91 52 97 51 14 " +
            "70 11 33 28 77 73 17 78 39 68 17 57 " +
            "91 71 52 38 17 14 91 43 58 50 27 29 48 " +
            "63 66 04 68 89 53 67 30 73 16 69 87 40 31 " +
            "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23 ")

    println("${Date()} - Result: $result")

}

fun maximumPathSum(treeNumbers: String) : Int {

    val tree : Array<Array<Int>> = Array(120) { Array(2) { 0 } }

    // Load the numbers into the tree
    val stringBuilder = StringBuilder()
    for ((treeIdx, i) in (treeNumbers.indices step 3).withIndex()) {

        stringBuilder.clear()
        stringBuilder.append(treeNumbers[i])
        stringBuilder.append(treeNumbers[i+1])

        tree[treeIdx][0] = stringBuilder.toString().toInt()

        //println("Number ${tree[treeIdx][0]}")
    }

    var rowNumber=0
    var elementInRowNumber=0
    var sum1 : Int
    var sum2 : Int
    var pointer1 : Int
    var pointer2 : Int
    tree.forEachIndexed { idx, numberAndSum ->

        // First node sum is the number itself
        if (idx == 0) {
            numberAndSum[1] = numberAndSum[0]

        } else {

            // Left most and right most can be summed only with one node
            when (elementInRowNumber) {
                0 -> { // Left most summed only with above-right
                    pointer1 = 0
                    pointer2 = 0
                }
                rowNumber -> { // Right most summed only with above-left
                    pointer1 = 1
                    pointer2 = 1
                }
                else -> {
                    pointer1 = 1
                    pointer2 = 0
                }
            }

            // Sum the node with each of its above nodes
            sum1 = numberAndSum[0] + tree[idx - rowNumber - pointer1][1]
            sum2 = numberAndSum[0] + tree[idx - rowNumber - pointer2][1]

            // The maximum among the possible result is kept as value to be summed in the next steps
            numberAndSum[1] = max(sum1, sum2)
        }

        if (elementInRowNumber == rowNumber) { // If it's the last element in the row
            rowNumber++
            elementInRowNumber=0
        } else {
            elementInRowNumber++
        }
    }

    // The max should be done only on the nodes of the last row,
    // but doing it on every node is easier and correct anyway
    return tree.maxOf { numberAndSum -> numberAndSum[1] }
}