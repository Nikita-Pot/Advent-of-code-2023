//The Elf would first like to know which games would have been possible
// if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

fun main() {
    val input = readInput("inputDay02")
   val answer= part1(input)
    println("answer of part1: $answer")
    val answer2=part2(input)
    println("answer of part2: $answer2")

}
private fun part1(input: List<String>) :Int{
    return input.sumOf { line->
    var cubes =0
    var gameId= 0
    val start = line.indexOfFirst { it == ':' }
    var  index =start

    while(index < line.length) {

        gameId = line.substring(5, start).toInt()


        if (line[index].isDigit()) {
            cubes = line[index++] - '0'

            while(line[index].isDigit()){ cubes = cubes * 10 + (line[index] - '0')
            index++
            }
        }
            when (line[index]) {
                'r' -> {
                   // println(" game: $gameId cubes = $cubes color: red max 12")
                    if (cubes > 12) { return@sumOf 0 }
                }
                'b' -> {

                    //println("game: $gameId cubes = $cubes color: blue max 13")
                    if (cubes > 13){return@sumOf 0}
                }
                'g' -> {
                    //println("game: $gameId cubes = $cubes color: green max 14")
                    if (cubes > 14) {return@sumOf 0}
                }
        }
        index++
    }
gameId
}
}


private fun part2(input: List<String>):Int{

    return input.sumOf { line ->
        var cubes =0
        var red =0
        var blue = 0
        var green =0
        val start = line.indexOfFirst { it == ':' }
        var  index =start

        while(index < line.length) {

            if (line[index].isDigit()) {
                cubes = line[index++] - '0'

                while(line[index].isDigit()){ cubes = cubes * 10 + (line[index] - '0')
                    index++
                }
            }
            when (line[index]) {
                'r' -> {
                    if (cubes>red){red=cubes}
                    index += 4
                }
                'b' -> {
                    if (cubes>blue){blue=cubes}
                    index += 5
                }
                'g' -> {
                    if (cubes>green){green=cubes}
                    index += 6
                }
            }
            index++
        }
        println("$red *$green * $blue = ${red*green*blue}")
        red*green*blue
    }

}