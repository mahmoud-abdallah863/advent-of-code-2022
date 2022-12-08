package day07

import readInput
import java.util.UUID
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
    val testInput = readInput("day07/Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("day07/Day07")
    println(part1(input))
    println(part2(input))

}


/**
 * Example
 *     val files1 = listOf(
 *         MyFile("a1", 1),
 *         MyFile("b1", 1),
 *         MyFile("c1", 1)
 *     )
 *     val directory1 = MyDirectory("Testing1", files = files1)
 *
 *     val files2 = listOf(
 *         MyFile("a2", 4),
 *         MyFile("b2", 4),
 *         MyFile("c2", 4)
 *     )
 *     val directory2 = MyDirectory("Testing2", files = files2)
 *
 *     val files = listOf(
 *         MyFile("a", 2),
 *         MyFile("b", 2),
 *         MyFile("c", 2)
 *     )
 *     val directory = MyDirectory("Testing2", directories = listOf(directory1, directory2), files = files)
 *     println(directory)
 *     println(directory.calculateSize())
 */

// 70000000 - 48381165
// 21,618,835
// 30,000,000


// 1844187
// 4978279