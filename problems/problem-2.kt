package problems

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms.
 * By starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
 *
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
 */
fun main() {

    val sum = fibonacciEvenSum(1, 2)

    println("Sum is $sum")
}

fun fibonacciEvenSum(num1: Int, num2: Int) : Int {

    // Recursion exit condition
    if (num2 > 4_000_000) {
        return if (num1.mod(2)==0) num1
        else 0
    }

    return if (num1.mod(2) == 0) num1 + fibonacciEvenSum(num2, num1+num2)
    else fibonacciEvenSum(num2, num1+num2)
}