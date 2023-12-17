import kotlin.math.abs

fun addEmptyLineAfterNonHash(input: List<String>): List<String> {
    val result = mutableListOf<String>()

    for (line in input) {
        result.add(line)

        // Controleer of de huidige regel geen '#' bevat
        if (!line.contains('#')) {
            // Voeg een nieuwe regel '......' toe
            result.add(".".repeat(line.length))
        }
    }

    return result
}

fun main() {
    val testinput = readInput("Day11Test")
    val input = readInput("inputDay11")
    check(part1(testinput) == 374L)
    println("part one: ${part1(input)}")
    //check(part2(testinput) == 2)
    //println("part two: ${part2(input)}")
}

private fun part1(input: List<String>): Long {
    val completeInput = addEmptyLineAfterNonHash(input)
    val size = completeInput.size
    val lenght = completeInput[0].length
    val coordinatesOfGalaxy = mutableListOf<IntArray>()

    var extraColom = 0
    for (x in 0..lenght) {
        var galaxycheck = false
        for (y in 0..size) if (isCharactertWithBoundCheck(y, x, completeInput) && isAGalaxy(y, x, completeInput)) {
            galaxycheck = true

            coordinatesOfGalaxy.add(intArrayOf(y, (x + extraColom)))

        }
        if (galaxycheck == false) {
            extraColom++
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
            // println("a = $currentCoordinate , b = $nextCoordinate, newdistance = $distance")
        }
    }

    println("answer of part one: $distance")
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

    val (xA, yA) = coorinatesA
    val (xB, yB) = coorinatesB
    val verschilX = abs(xB - xA)
    val verschilY = abs(yB - yA)
    val distance = verschilX + verschilY
    return distance


}

