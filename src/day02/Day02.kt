package day02

import readInput

enum class RoundResult(val score: Int) {
    Win(6),
    Lost(0),
    Draw(3);

    companion object {
        fun getFromString(s: Char): RoundResult = when(s) {
            'X' -> Lost
            'Y' -> Draw
            else -> Win
        }
    }
}

enum class OptionShape(
    private val index: Int,
    val selectScore: Int
) {
    Rock(0, 1),
    Paper(1, 2),
    Scissors(2, 3);

    companion object {
        fun getFromString(s: Char): OptionShape = when(s) {
            'A', 'X' -> Rock
            'B', 'Y' -> Paper
            else -> Scissors
        }
    }

    fun battle(other: OptionShape): RoundResult =
        if (this.index == other.index) RoundResult.Draw
        else if (other.index == (this.index + 1).mod(values().size)) RoundResult.Lost
        else RoundResult.Win

    fun findMoveForSecondPlayer(roundResult: RoundResult): OptionShape = when (roundResult) {
        RoundResult.Draw -> this
        RoundResult.Lost -> {
            val index = (this.index - 1).mod(values().size)
            values()[index]
        }
        else -> {
            val index = (this.index + 1) % values().size
            values()[index]
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int = input
        .sumOf { line ->
            val lineAsCharArray = line.toCharArray()
            val p1Shape = OptionShape.getFromString(lineAsCharArray[0])
            val p2Shape = OptionShape.getFromString(lineAsCharArray[2])
            p2Shape.selectScore + p2Shape.battle(p1Shape).score
        }

    fun part2(input: List<String>): Int {
        var score = 0
        input.forEach { line ->
            val lineAsCharArray = line.toCharArray()
            val p1Shape = OptionShape.getFromString(lineAsCharArray[0])
            val roundResult = RoundResult.getFromString(lineAsCharArray[2])
            score += roundResult.score + p1Shape.findMoveForSecondPlayer(roundResult).selectScore
        }
        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}
