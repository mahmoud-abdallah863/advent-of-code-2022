package day01

import assertEquals
import readInput
import readTestInput
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
    val testInput = readTestInput()
    assertEquals(part1(testInput), 24000)
    assertEquals(part2(testInput), 45000)

    val input = readInput()
    println(part1(input))
    println(part2(input))
}
