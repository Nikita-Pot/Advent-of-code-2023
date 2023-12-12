import java.io.Serializable

val testinput = listOf(
    "467..114..\n",
    "...*......\n",
    "..35..633.\n",
    "......#...\n",
    "617*......\n",
    ".....+.58.\n",
    "..592.....\n",
    "......755.\n",
    "...\$.*....\n",
    ".664.598.."
)

fun main() {
    val input = readInput("inputDay03")
    part1(input)
    check(part2(testinput) == 467835)
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

    //    private var key = intArrayOf(x)[y]
//    fun getNumber():Int
//    {
//        return number
//    }
    fun getPair(): Pair<Int, Int> {
        return Pair(y, x)
    }
}

private fun part2(input: List<String>): Int {
    val array = testinput
    val arrayLength = array.size
    val lineLength = array[0].length
    val coordinatesOfDigits = mutableListOf<IntArray>()
    val charactersOfNumber = mutableListOf<Char>()
    var answer = 0
    // var x: Int
    val partNumberList = mutableListOf<PartNumber>()
    val validPartnumbers = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
    for (y in 0 until arrayLength) {
        var x = 0

        while (isCharacterDigitWithBoundCheck(y, x, array)) {
            coordinatesOfDigits.add(intArrayOf(y, x))
            charactersOfNumber.add(array[y][x])
            x++
        }
        var index = 0
        val line = array[y]
        var partNumber = 0
        x = 0
        while (index < line.length) {

            if (line[index].isDigit()) {
                partNumber = line[index] - '0'
                index++
                while (line[index].isDigit()) {
                    partNumber = partNumber * 10 + (line[index] - '0')
                    index++
                }
            }
            if (partNumber != 0 && !line[index].isDigit()) {
                partNumberList.add(PartNumber(y, index, partNumber))
                println("$y,$index,$partNumber")
                partNumber = 0
            }
            index++
        }
    }

    println("Time to check")
    partNumberList.forEach { partNumber ->
        println("partnumber to check: ${partNumber.number}, ${partNumber.getPair()}")
        val resultPair = checkNeighbours2(partNumber.getPair(), arrayLength, lineLength, partNumber.length, array)
        if (resultPair != Pair(0, 0)) {
            val numberPair = partNumber.getPair()
            println("coordinaten gear: $resultPair, coordinaten number : $numberPair, number: ${partNumber.number}")
            val (x, y) = resultPair
            val number = partNumber.number
            validPartnumbers.getOrPut(Pair(y, x)) { mutableListOf() }.add(number)
            coordinatesOfDigits.clear()
            charactersOfNumber.clear()
            println("add gear")
        }

    }
    println("time for math")
    validPartnumbers.forEach { (key, value) ->
        if (value.size > 1) {
            answer += value[0] * value[1]
            println("${value[0]} * ${value[1]}")
            println("new add")
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
    val input = array[yNumber][xNumber]
    var i = xNumber - partNumberLength - 1
    var j = -1
    val numberBoundCheck = partNumberLength + 2
    println("start = $input")
    if(yNumber-1 <0){j=0}
    while (j <= 1) {
        while (i < 0) {
            i++
        }
        val newYNumber = yNumber + j
        if (isInCustomBound(i, newYNumber, arrayLength, lineLength)) {
            println("y waarde: $newYNumber")

            while (i <= numberBoundCheck) {
                if (i in 1..<lineLength) {
                    val charToCheck = array[newYNumber][i]
                    //println(charToCheck)
                    if (!Character.isDigit(charToCheck) && charToCheck == '*') {
                        println("$yNumber,$i,$charToCheck")
                        result = Pair(newYNumber, i)
                    }
                    i++
                }
                i++
            }
            j++
            i=xNumber - partNumberLength - 1
        }
        else j++

//            if (!isInBound(x, y, arrayLength, lineLength)) {
//                break
//            } else {
//                val charToCheck = array[y][x]
//                if (!Character.isDigit(charToCheck) && charToCheck == '*') {
//                    println("$y,$x,$charToCheck")
//                    result = Pair(y, x)
//                    //return Pair(x, y)
//                }
//            }
    }

    return result
}
fun isInCustomBound(x: Int, y: Int, arrayLength: Int, lineLength: Int): Boolean {
    return x in 0 until lineLength && y in 0 until arrayLength
}



