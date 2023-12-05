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
var lastCheck=0
var match :Boolean = false
fun main() {

    val input = readInput("inputDay03")
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
        input.forEach { line->
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
    return coordinatesOfDigits.any { checkNeighbours(it, arrayLength, lineLength) { isASymbol(it[0], it[1], array) }
    }
}

fun isASymbol(x: Int, y: Int, array: List<String>): Boolean {
    val charToCheck = array[y][x]
    return !Character.isDigit(charToCheck) && charToCheck != '.'
}

fun checkNeighbours(coordinates: IntArray, arrayLength: Int, lineLength: Int, predicate: (IntArray) -> Boolean): Boolean {
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

private fun part2(input: List<String>):Int {
    val array = input
    val arrayLength = array.size
    val lineLength = array[0].length
    val coordinatesOfDigits = mutableListOf<IntArray>()
    val charactersOfNumber = mutableListOf<Char>()
    var answer = 0
    var x: Int
    var lastnumbers =0
    for (y in 0 until arrayLength) {
        x = 0

        input.forEach { line->
            while (isCharacterDigitWithBoundCheck(x, y, array)) {
                coordinatesOfDigits.add(intArrayOf(x, y))
                charactersOfNumber.add(array[y][x])
                x++
            }

            if (isPartNumber2(coordinatesOfDigits, array, arrayLength, lineLength)) {

                if (match == false)
                    lastnumbers = charactersOfNumber.joinToString("").toInt()
                //println("last match: $lastnumbers")
                if(match == true){
                    var newnumber = charactersOfNumber.joinToString("").toInt()
                    println("lastnumber: $lastnumbers, newnumber: $newnumber")
                    val math =lastnumbers * newnumber
                  answer+= math
                    println(answer)
                    match=false}
            }
            coordinatesOfDigits.clear()
            charactersOfNumber.clear()
            x++
        }
    }
    println("answer of part two: $answer")
    return answer
}
fun isAGear(x: Int, y: Int, array: List<String>): Boolean {
    val charToCheck = array[y][x]
    return if (!Character.isDigit(charToCheck) && charToCheck == '*') {
        if(lastCheck!= 0 && lastCheck=="$x$y".toIntOrNull()){match=true}
        else{ lastCheck= "$x$y".toIntOrNull()!!
        }
       // println(match)
        true
    } else false}

fun isPartNumber2(coordinatesOfDigits: List<IntArray>, array: List<String>, arrayLength: Int, lineLength: Int): Boolean {
    return coordinatesOfDigits.any { checkNeighbours(it, arrayLength, lineLength) { isAGear(it[0], it[1], array) }
    }
}
