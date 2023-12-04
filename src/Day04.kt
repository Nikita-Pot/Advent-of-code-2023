val testinput = listOf("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" ,
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" ,
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" ,
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" ,
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" ,
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")

fun main() {
    val input = readInput("inputDay04")
    part1(input)
    part2(input)


}
private fun part1(input: List<String>) {
    var matchingCount =0
    input.forEach { card ->
        val winningNumbers = card.substringAfter(":").substringBefore("|").trim()
        val numbers = card.substringAfter("|").trim()

        val winningNumbersList = winningNumbers.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val filteredNumbers = numbers.split(" ").filter { it.isNotBlank() }.mapNotNull { if (it.isNotBlank()) it.toIntOrNull() else null }
            .filter { it in winningNumbersList }
       matchingCount+= 1 shl (filteredNumbers.size - 1)
        println("Winning Numbers: $winningNumbers")
        println("All Numbers: $numbers")
        println("Filtered Numbers: $filteredNumbers")
        println("matching numbers count: $matchingCount")

    }
}

private fun part2(input: List<String>){}