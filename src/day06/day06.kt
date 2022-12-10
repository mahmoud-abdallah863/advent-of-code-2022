package day06

import assertEquals
import readInput
import readTestInput

fun main() {

    fun findDistinctCharactersIndex(s: String, size: Int, step: Int = 1): Int {
        s.windowed(size, step = step).forEachIndexed { index, window ->
            if (window.toSet().size == size) {
                return index + size
            }
        }
        return -1
    }

    fun part1(input: List<String>): Int {
        input.forEach { line ->
            return findDistinctCharactersIndex(line, 4)
        }
        return -1
    }


    fun part2(input: List<String>): Int {
        input.forEach { line ->
            return findDistinctCharactersIndex(line, 14)
        }
        return -1
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput()
    assertEquals(part1(testInput), 5)
    assertEquals(part2(testInput), 23)

    val input = readInput()
    println(part1(input))
    println(part2(input))

}