fun main() {
    val input = readInput("inputDay01")
    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    var answer = 0
    input.forEach { line ->
        val firstDigit = line.first { it.isDigit() } // get first number
        val lastDigit = line.last { it.isDigit() }   // get last number
        //println("$firstDigit$lastDigit") //check output
        val number = "$firstDigit$lastDigit".toInt()  // set first and last number to one int
        answer += number // add all number to one answer
        number
    }
    println("the answer of part 1 is: $answer") // when whole document is done give the answer
}

private fun part2(input: List<String>) {
    val regex = Regex("(?=(one|two|three|four|five|six|seven|eight|nine|1|2|3|4|5|6|7|8|9))")

    val answer = input.sumOf { line ->
        val matches = regex.findAll(line).toList()
        val fMatch = matches.first()
        val lMatch = matches.last()
        val firstMatch = findRealDigit(fMatch.groups[1]?.value)
        val lastMatch = findRealDigit(lMatch.groups[1]?.value)
        val number = firstMatch * 10 + lastMatch  // set first and last number to one int
        number
    }
    println(answer)
}

fun findRealDigit(string: String?): Int {
    return when (string) {
        "one", "1" -> 1
        "two", "2" -> 2
        "three", "3" -> 3
        "four", "4" -> 4
        "five", "5" -> 5
        "six", "6" -> 6
        "seven", "7" -> 7
        "eight", "8" -> 8
        "nine", "9" -> 9
        else -> 0
    }
}