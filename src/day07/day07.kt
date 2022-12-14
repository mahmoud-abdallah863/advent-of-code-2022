package day07

import assertEquals
import readInput
import readTestInput
import kotlin.math.min

fun main() {

    fun calculateShit(directory: MyDirectory): Int {
        val currentDirSize = directory.calculateSize()
        val currentSize = if (currentDirSize < 100_000) currentDirSize else 0

        return currentSize + directory.directories.sumOf{ calculateShit(it) }
    }

    fun part1(input: List<String>): Int {
        val terminalParser = TerminalParser()
        val parentDirectory = terminalParser.parse(input)

        return calculateShit(parentDirectory)
    }


    fun part2(input: List<String>): Int {
        val terminalParser = TerminalParser()
        val rootDir = terminalParser.parse(input)
        val parentDirectorySize = rootDir.calculateSize()

        val freeSpace = 70_000_000 - parentDirectorySize
        val minSizeToBeDeleted = kotlin.math.abs(min(0, freeSpace - 30_000_000))

        val directorySizesList = rootDir.getDirectoriesSizesList().sorted()
        return directorySizesList.find { it >= minSizeToBeDeleted } ?: -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput()
    assertEquals(part1(testInput), 95437)
    assertEquals(part2(testInput), 24933642)

    val input = readInput()
    println(part1(input))
    println(part2(input))

}