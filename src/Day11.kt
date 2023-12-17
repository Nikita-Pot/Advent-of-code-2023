import kotlin.math.abs

fun main() {
    val testinput = readInput("Day11Test")
    val input = readInput("inputDay11")
    check(solve(testinput, 1) == 374L)
    println("part one: ${solve(input, 1)}")
    check(solve(testinput, 99) == 8410L)
    println("part two: ${solve(input, 999999)}")
}

private fun solve(input: List<String>, times: Int): Long {
    val size = input.size
    val lenght = input[0].length
    val coordinatesOfGalaxy = mutableListOf<IntArray>()
    var extraRow = 0

    for (y in 0..size) {// check elke y
        var emptyrow = true

        for (x in 0..lenght) { // check elke x in y

            if (isCharactertWithBoundCheck(y, x, input) && isAGalaxy(y, x, input)) {
                emptyrow = false
                var extraCollum = 0

                for (xCheck in 0..x) {
                    var emptyColumn = true

                    for (yCheck in 0..size) {

                        if (isCharactertWithBoundCheck(yCheck, xCheck, input) && isAGalaxy(yCheck, xCheck, input)) {
                            emptyColumn = false
                        }
                    }

                    if (emptyColumn == true) {
                        extraCollum += times
                    }
                }
                coordinatesOfGalaxy.add(intArrayOf((y + extraRow), (x + extraCollum)))
            }
        }
        if (emptyrow == true) {
            extraRow += times
        }
    }

    println("check coordinates")
    var distance = 0L

    for (i in coordinatesOfGalaxy.indices) {
        val currentCoordinate = coordinatesOfGalaxy[i]

        // Loop door de rest van de coördinaten en bereken de afstand
        for (j in (i + 1) until coordinatesOfGalaxy.size) {
            val nextCoordinate = coordinatesOfGalaxy[j]
            distance += calcDistance(currentCoordinate, nextCoordinate).toLong()
        }
    }
    println("answer: $distance times:$times")
    return distance
}

private fun isAGalaxy(y: Int, x: Int, array: List<String>): Boolean {
    val charToCheck = array[y][x]
    return charToCheck == '#'
}

private fun isCharactertWithBoundCheck(y: Int, x: Int, array: List<String>): Boolean {
    return y in array.indices && x in 0 until array[y].length
}

private fun calcDistance(coorinatesA: IntArray, coorinatesB: IntArray): Int {
    val (yA, xA) = coorinatesA
    val (yB, xB) = coorinatesB
    val verschilX = abs(xB - xA)
    val verschilY = abs(yB - yA)
    val distance = verschilX + verschilY
    return distance
}

