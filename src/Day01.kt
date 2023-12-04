fun main() {
    val input = readInput("inputDay01")
    part1(input)
    part2(input)

}

private fun part1(input: List<String>) {
    var answer = 0
    input.map { line ->
        val firstDigit = line.firstOrNull { it.isDigit() } // get first number
        val lastDigit = line.lastOrNull { it.isDigit() }   // get last number
        //println("$firstDigit$lastDigit") //check output
        var number = "$firstDigit$lastDigit".toIntOrNull()  // set first and last number to one int
        if (number != null) {
            answer += number // add all number to one answer
        }
    }
    println("the answer of part 1 is: $answer") // when whole document is done give the answer
}

private fun part2(input: List<String>) {
    var answer = 0
    var count = 0
    val regex = Regex("(?=(one|two|three|four|five|six|seven|eight|nine|1|2|3|4|5|6|7|8|9))")


    for (inputString in input) {
        var fmatch: String? = null
        var lmatch: String?
        var firstmatch = 0
        var lastmatch = 0

        regex.findAll(inputString).forEach { match ->
            if (fmatch == null) {
                fmatch = match.groups[1]?.value
                firstmatch = findRealDigit(fmatch)
            } else {
                lmatch = match.groups[1]?.value
                lastmatch = findRealDigit(lmatch)

            }
        }
        if (lastmatch == 0) lastmatch = firstmatch
        val number = firstmatch * 10 + lastmatch  // set first and last number to one int
        answer += number // add all number to one answer
        count++
        println("number: $number, count: $count")

    }
    println("the answer of part 2 is: $answer count = $count")


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