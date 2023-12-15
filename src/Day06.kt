fun main() {
    val testinput = readInput("Day06Test")
    val input = readInput("inputDay06")
    check(part1(testinput) == 288)
    part1(input)
    //check(part2(testinput))

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
            println("maxDistance: $maxDistance, maxTime = $maxTime, j:$j ")
            if (maxDistance > distance[i]) {
                newanswer++
            }
        }

        answer *= newanswer
        newanswer = 0
        println(answer)
    }
    return answer
}