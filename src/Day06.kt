fun main() {
    val testinput = readInput("Day06Test")
    val input = readInput("inputDay06")
    check(part1(testinput) == 288)
    println("part one: ${part1(input)}")
    check(part2(testinput) == 71503L)
    println("part two: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    val whitespaceRegex = Regex("\\s+")
    val time = input[0].substringAfter(':').trim().split(whitespaceRegex).map { it.toInt() }
    val distance = input[1].substringAfter(':').trim().split(whitespaceRegex).map { it.toInt() }
    var answer = 1

    for (i in time.indices) {
        var left = 0
        var right = time[i]
        val maxTime = time[i]
        val recordDistance = distance[i]
        while (left <= right) {
            val mid = left + (right - left) / 2
            val maxDistance = mid * (maxTime - mid)

            if (maxDistance > recordDistance) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        if (maxTime and 1 == 0) {
            answer *= ((maxTime shr 1) - left) * 2 + 1
        } else {
            answer *= ((maxTime shr 1) - left) * 2 + 2
        }
    }

    return answer
}

private fun part2(input: List<String>): Long {
    val whitespaceRegex = Regex("\\s+")
    val time = input[0].substringAfter(':').trim().split(whitespaceRegex).joinToString("").toLong()
    val distance = input[1].substringAfter(':').trim().split(whitespaceRegex).joinToString("").toLong()
    var left = 14L
    var right = time

    while (left <= right) {
        val mid = left + (right - left) / 2
        val maxDistance = mid * (time - mid)

        if (maxDistance > distance) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    if (time and 1L == 0L) {
        return ((time shr 1) - left) * 2 + 1
    } else {
        return ((time shr 1) - left) * 2 + 2
    }
}