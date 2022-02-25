package problems

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 *
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
fun main() {

    val result=productOfPythagoreanTriplet(1000)

    println("Result: $result")
}

fun productOfPythagoreanTriplet(sumOfTriplet: Int) : Int {

    for (a in 0..sumOfTriplet) {

        for (b in (a+1)..sumOfTriplet) {

            for (c in (b+1)..sumOfTriplet) {

                if ((a+b+c==sumOfTriplet) && (a*a+b*b-c*c==0)) {
                    println("a: $a, b: $b, c: $c")
                    return a*b*c
                }
            }
        }
    }

    return 0
}
