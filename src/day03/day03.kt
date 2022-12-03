package day03

import readInput

fun main() {

    fun part1(input: List<String>): Int = input
        .sumOf { line ->
            val (compartment1, compartment2) = line.chunked(line.length / 2).map { it.toSet() }
            val commonCharacters = compartment1.intersect(compartment2)
            commonCharacters.sumOf { char ->
                if (char.isLowerCase()) char - 'a' + 1
                else char - 'A' + 27
            }
        }

    fun part2(input: List<String>): Int = input
        .chunked(3)
        .sumOf { rucksack ->
            val commonCharacter = rucksack
                .asSequence()
                .map { it.toSet() }
                .reduce { acc, chars -> acc.intersect(chars) }
                .firstOrNull() ?: return@sumOf 0
          if (commonCharacter.isLowerCase()) commonCharacter - 'a' + 1
          else commonCharacter - 'A' + 27
        }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}
