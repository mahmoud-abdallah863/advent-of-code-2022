package day05

import assertEquals
import readInput
import readTestInput

data class Instruction(
    val moveCount: Int,
    val fromStackIndex: Int,
    val toStackIndex: Int
)

fun main() {

    fun readStacks(input: List<String>): MutableList<MutableList<String>> {
        val output = mutableListOf<MutableList<String>>()
        var filteredLines = input.map { line -> line
            .toList()
            .chunked(4)
            .map { it.joinToString(separator = "").trim() }
        }


        var indexOfNumbersLine = 0
        filteredLines.forEachIndexed { index, strings ->
            if (strings.isNotEmpty() && strings.first().toIntOrNull() != null) {
                indexOfNumbersLine = index
                return@forEachIndexed
            }
        }
        filteredLines = filteredLines.subList(0, indexOfNumbersLine)

        filteredLines.forEach {line ->
            line.forEachIndexed { index, crate ->
                if (crate.isEmpty()) return@forEachIndexed
                if (output.size <= index+1) {
                    repeat(index+1 - output.size) { output.add(mutableListOf()) }
                }

                output[index].add(crate)
            }
        }

        return output
    }

    fun readInstructions(input: List<String>): List<Instruction> {
        val output = mutableListOf<Instruction>()

        val emptyLineIndex = input.indexOfFirst { it.trim().isEmpty() }
        for (lineIndex in (emptyLineIndex+1 until input.size)) {
            val a = input[lineIndex].split(" ").filterIndexed{index, _ -> index%2==1}
            val newInstruction = Instruction(
                moveCount = a[0].toInt(),
                fromStackIndex = a[1].toInt()-1,
                toStackIndex = a[2].toInt()-1,
            )
            output.add(newInstruction)
        }

        return output
    }

    fun part1(input: List<String>): String {
        val stacks = readStacks(input)

        val instructions = readInstructions(input)
        instructions.forEach { instruction ->
            repeat(instruction.moveCount){
                val element = stacks[instruction.fromStackIndex].removeFirst()
                stacks[instruction.toStackIndex].add(0, element)
            }
        }
        return stacks.joinToString(separator = "") { it.first().filter { c -> c.isLetter() } }

    }

    fun part2(input: List<String>): String {
        val stacks = readStacks(input)

        val instructions = readInstructions(input)
        instructions.forEach { instruction ->
            repeat(instruction.moveCount){ index ->
                val element = stacks[instruction.fromStackIndex].removeFirst()
                stacks[instruction.toStackIndex].add(index, element)
            }
        }
        return stacks.joinToString(separator = "") { it.first().filter { c -> c.isLetter() } }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput()
    assertEquals(part1(testInput), "CMZ")
    assertEquals(part2(testInput), "MCD")

    val input = readInput()
    println(part1(input))
    println(part2(input))
}
