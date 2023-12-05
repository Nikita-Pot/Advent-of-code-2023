fun main() {
    val input = readInput("inputDay04")
    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    var matchingCount = 0
    input.forEach { card ->
        val winningNumbers = card.substringAfter(":").substringBefore("|").trim()
        val numbers = card.substringAfter("|").trim()

        val winningNumbersList = winningNumbers.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val filteredNumbers =
            numbers.split(" ").filter { it.isNotBlank() }.mapNotNull { if (it.isNotBlank()) it.toIntOrNull() else null }
                .filter { it in winningNumbersList }
        matchingCount += 1 shl (filteredNumbers.size - 1)
        // println("Winning Numbers: $winningNumbers")
        // println("All Numbers: $numbers")
        // println("Filtered Numbers: $filteredNumbers")
        //println("matching numbers count: $matchingCount")
    }
}

private fun part2(input: List<String>) {
    val n = input.size
    val copies = ArrayList<Int>(n)
    var cardnumber = 0
    var som = 0

    for (i in 1..n) {
        copies.add(1)
    }

    input.forEach { card ->
        var j = 1
        var numberOfCards = copies[cardnumber]
        val winningNumbers = card.substringAfter(":").substringBefore("|").trim()
        val numbers = card.substringAfter("|").trim()
        val winningNumbersList = winningNumbers.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val filteredNumbers =
            numbers.split(" ").filter { it.isNotBlank() }.mapNotNull { if (it.isNotBlank()) it.toIntOrNull() else null }
                .filter { it in winningNumbersList }
        // println("cardnumber: $cardnumber")
        //println("number of card $numberOfCards")

        while (j < filteredNumbers.size + 1) {
            copies[cardnumber + j] += numberOfCards
            j++
        }
        cardnumber++
    }
    for (allcopies in copies) {
        som += allcopies
    }
    println("Answer part 2 geeft total copies: $som")
}