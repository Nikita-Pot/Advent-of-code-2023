fun main()
{
    val input = readInput("inputDay01")
    part1(input)
    //part2(input).println()
}
fun part1(input: List<String>)
{
    var answer = 0
     input.map { line ->
                val firstDigit = line.firstOrNull { it.isDigit() } // get first number
                val lastDigit = line.lastOrNull { it.isDigit() }   // get last number
                //println("$firstDigit$lastDigit") //check output
                var number = "$firstDigit$lastDigit".toIntOrNull()  // set first and last number to one int
                if (number != null)
                {
                answer+= number // add all number to one answer
                }
             }
println(answer) // when whole document is done give the answer
}


fun part2(input: List<String>): Int {
    return input.size
}
