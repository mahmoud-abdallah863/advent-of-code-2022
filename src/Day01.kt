import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var maxCalories = 0
        var caloriesSum = 0
        input.forEach { line ->
            if (line.isBlank()){
                maxCalories = max(maxCalories, caloriesSum)
                caloriesSum = 0
            }else {
                caloriesSum += line.trim().toInt()
            }
        }
        return maxCalories
    }

    fun part2(input: List<String>): Int {
        val cachedData = mutableListOf<Int>()
        var currentSum = 0
        input.forEach { line ->
            if (line.isBlank()) {
                cachedData.add(currentSum)
                currentSum = 0
            }else {
                currentSum += line.trim().toInt()
            }
        }
        cachedData.add(currentSum)

        cachedData.sortDescending()
        return cachedData.take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
