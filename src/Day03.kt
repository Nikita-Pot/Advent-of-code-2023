fun main() {
    val input = readInput("inputDay03")
    val testinput = readInput("Day03Test")
    check(part1(testinput) == 4361)
    check(part2(testinput) == 467835)
    part1(input)
    part2(input)
}

private fun part1(input: List<String>): Int {
    val array = input
    val arrayLength = array.size
    val lineLength = array[0].length
    val coordinatesOfDigits = mutableListOf<IntArray>()
    val charactersOfNumber = mutableListOf<Char>()
    var answer = 0
    var x: Int

    for (y in 0 until arrayLength) {
        x = 0
        input.forEach { line ->
            while (isCharacterDigitWithBoundCheck(x, y, array)) {
                coordinatesOfDigits.add(intArrayOf(x, y))
                charactersOfNumber.add(array[y][x])
                x++
            }

            if (isPartNumber(coordinatesOfDigits, array, arrayLength, lineLength)) {
                //println(charactersOfNumber.joinToString("").toInt())// check number
                answer += charactersOfNumber.joinToString("").toInt()
            }
            coordinatesOfDigits.clear()
            charactersOfNumber.clear()
            x++
        }
    }
    println("answer of part one: $answer")
    return answer
}

private fun isCharacterDigitWithBoundCheck(x: Int, y: Int, array: List<String>): Boolean {
    return y in array.indices && x in 0 until array[y].length && array[y][x].isDigit()
}

fun isPartNumber(coordinatesOfDigits: List<IntArray>, array: List<String>, arrayLength: Int, lineLength: Int): Boolean {
    return coordinatesOfDigits.any {
        checkNeighbours(it, arrayLength, lineLength) { isASymbol(it[0], it[1], array) }
    }
}

fun isASymbol(x: Int, y: Int, array: List<String>): Boolean {
    val charToCheck = array[y][x]
    return !Character.isDigit(charToCheck) && charToCheck != '.'
}

fun checkNeighbours(
    coordinates: IntArray, arrayLength: Int, lineLength: Int, predicate: (IntArray) -> Boolean
): Boolean {
    val directionsX = intArrayOf(1, 1, 1, 0, -1, -1, -1, 0)
    val directionsY = intArrayOf(-1, 0, 1, 1, 1, 0, -1, -1)

    return directionsX.indices.any {
        val x = coordinates[0] + directionsX[it]
        val y = coordinates[1] + directionsY[it]
        isInBound(x, y, arrayLength, lineLength) && predicate(intArrayOf(x, y))
    }
}

fun isInBound(x: Int, y: Int, arrayLength: Int, lineLength: Int): Boolean {
    return y in 0 until arrayLength && x in 0 until lineLength
}

class PartNumber(val y: Int, val x: Int, val number: Int) {
    var length = number.toString().length

    fun getPair(): Pair<Int, Int> {
        return Pair(y, x)
    }
}

private fun part2(input: List<String>): Int {
    val array = input
    val arrayLength = array.size
    val lineLength = array[0].length
    var answer = 0
    val partNumberList = mutableListOf<PartNumber>()
    val validPartnumbers = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

    for (y in 0 until arrayLength) {
        var index = 0
        val line = array[y]
        var partNumber = 0

        while (index < lineLength - 1) {

            if (line[index].isDigit()) {
                partNumber = line[index] - '0'
                index++

                while (line[index].isDigit()) {
                    partNumber = partNumber * 10 + (line[index] - '0')
                    index++
                }
            }
            if (partNumber != 0 && !line[index].isDigit()) {
                partNumberList.add(PartNumber(y, index - 1, partNumber))
                partNumber = 0
            }
            index++
        }
    }

    partNumberList.forEach { partNumber ->
        val resultPair = checkNeighbours2(partNumber.getPair(), arrayLength, lineLength, partNumber.length, array)
        if (resultPair != Pair(0, 0)) {
            val (x, y) = resultPair
            val number = partNumber.number
            validPartnumbers.getOrPut(Pair(y, x)) { mutableListOf() }.add(number)
        }
    }
    validPartnumbers.forEach { (key, value) ->
        if (value.size > 1) {
            answer += value[0] * value[1]
        }
    }
    println("answer of part two: $answer")
    return answer

}

fun checkNeighbours2(
    coordinates: Pair<Int, Int>, arrayLength: Int, lineLength: Int, partNumberLength: Int, array: List<String>
): Pair<Int, Int> {
    var (yNumber, xNumber) = coordinates
    var result = Pair(0, 0)
    var xmin = xNumber - partNumberLength
    var xmax = xNumber + 1
    var ymin = yNumber - 1
    var ymax = yNumber + 1

    if (xmin < 0) {
        xmin = 0
    }
    if (xmax > lineLength) {
        xmax = lineLength
    }
    if (ymin < 0) {
        ymin = 0
    }
    if (ymax == arrayLength) {
        ymax = arrayLength - 1
    }
    for (y in ymin..ymax) {

        for (x in xmin..xmax) {
            val charToCheck = array[y][x]
//            println("line: $y, x: $x, char:$charToCheck")
            if (!Character.isDigit(charToCheck) && charToCheck == '*') {
                result = Pair(y, x)
            }
        }
    }
    return result
}