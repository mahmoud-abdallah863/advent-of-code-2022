package day04

import assertEquals
import readInput
import readTestInput

fun main() {

    fun toListOfSets(input: List<String>): List<Pair<Set<Int>, Set<Int>>> = input
        .map { line ->
            val (set1, set2) = line
                .split(",")
                .map {
                    val (first, second) = it.split("-")
                    (first.toInt()..second.toInt()).toSet()
                }
            set1 to set2
        }

    fun part1(input: List<String>): Int = toListOfSets(input)
        .sumOf { (set1, set2) ->
            (if ((set1 intersect set2) == set2 || (set2 intersect set1) == set1) 1 else 0) as Int
        }

    fun part2(input: List<String>): Int = toListOfSets(input)
        .sumOf { (set1, set2) ->
            (if ((set1 intersect set2).isNotEmpty() || (set2 intersect set1).isNotEmpty()) 1 else 0) as Int
        }


    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput()
    assertEquals(part1(testInput), 2)
    assertEquals(part2(testInput), 4)

    val input = readInput()
    println(part1(input))
    println(part2(input))
}
