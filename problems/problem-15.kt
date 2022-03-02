package problems

import java.lang.Exception
import java.util.*

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 *
 * How many such routes are there through a 20×20 grid?
 */
fun main() {

    val nParallel = 1

    println("${Date()} - Start with $nParallel parallel processes")

    val result =
        calculateNumberOfRoutesFromTopLeftToBottomRight(20, 0, 0)

    println("${Date()} - Result: $result")
}

fun calculateNumberOfRoutesFromTopLeftToBottomRight(gridSideLength: Int, x: Int, y:Int) : Long {

    if (x == gridSideLength && y == gridSideLength)
        return 1

    else if (x < gridSideLength && y < gridSideLength)
        return calculateNumberOfRoutesFromTopLeftToBottomRight(gridSideLength, x+1, y) +
                calculateNumberOfRoutesFromTopLeftToBottomRight(gridSideLength, x, y+1)

    else if (x < gridSideLength)
        return calculateNumberOfRoutesFromTopLeftToBottomRight(gridSideLength, x+1, y)

    else if (y < gridSideLength)
        return calculateNumberOfRoutesFromTopLeftToBottomRight(gridSideLength, x, y+1)

    throw Exception("Point not to be reached")

}