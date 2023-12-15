fun main() {
    val testinput = readInput("Day06Test")
    val input = readInput("inputDay06")
    check(part1(testinput) == 288)
    println("part one: ${part1(input)}")
    check(part2(testinput) == 71503)
    println("part two: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    val whitespaceRegex = Regex("\\s+")
    val time = input[0].substringAfter(':').trim().split(whitespaceRegex).map { it.toInt() }
    val distance = input[1].substringAfter(':').trim().split(whitespaceRegex).map { it.toInt() }
    var answer = 1
    var newanswer = 0

    for (i in time.indices) {
        val maxTime = time[i]

        for (j in 1..maxTime) {
            val maxDistance = j * (maxTime - j)

            if (maxDistance > distance[i]) {
                newanswer++
            }
        }

        answer *= newanswer
        newanswer = 0
    }
    return answer
}

private fun part2(input: List<String>): Int {
    val whitespaceRegex = Regex("\\s+")
    val time = input[0].substringAfter(':').trim().split(whitespaceRegex).joinToString("").toLong()
    val distance = input[1].substringAfter(':').trim().split(whitespaceRegex).joinToString("").toLong()
    var answer = 0
    for (j in 14..time) {
        val maxDistance = j * (time - j)

        if (maxDistance > distance) {
            answer++
        }
    }
    return answer
}