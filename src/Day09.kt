import kotlin.math.absoluteValue

fun main() {
    val testinput = readInput("Day09Test")
    val input = readInput("inputDay09")
    check(part1(testinput) == 114)
    println("part one: ${part1(input)}")
//  check(part2(testinput))
//    println("part two: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    return input.sumOf { line ->
        val numbers = line.split(' ')
        val arrayOfNumbers = IntArray(numbers.size) { numbers[it].toInt() }
        val answer = arrayOfNumbers.last() + calcNext(arrayOfNumbers)
        answer
    }
}


private fun calcNext(line: IntArray): Int {
    var differenceToAdd = 0
    var lineSize = line.size - 1

    while (lineSize >= 0) {
        val isNull = areAllZeroDifferences(line, lineSize)// check if al difference give null

        if (isNull) {
            return differenceToAdd // when al differences are null return differenceToAdd
        }
        lineSize--
        differenceToAdd += line[lineSize]
        // println("difference: $differenceToAdd ")// check difference
    }
    return 0
}

private fun areAllZeroDifferences(line: IntArray, lineSize: Int): Boolean {
    var isNull = true
    for (i in 0 until lineSize) {
        val difference = line[i + 1] - line[i]
        line[i] = difference
        isNull = isNull && difference == 0
    }
    return isNull
}
