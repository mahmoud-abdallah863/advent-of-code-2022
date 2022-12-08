package day07

import java.util.*

data class MyDirectory(
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val parentDirUUID: String? = null,
    val directories: MutableList<MyDirectory> = mutableListOf(),
    val files: MutableList<MyFile> = mutableListOf()
) {
    fun isRootDirectory(): Boolean = parentDirUUID == null
    fun calculateSize(): Int =
        files.sumOf { it.size } + directories.sumOf { it.calculateSize() }

    fun getDirectoriesSizesList(): List<Int> = listOf(this.calculateSize()) +
            directories.flatMap { it.getDirectoriesSizesList() }


    override fun toString(): String = toString(0)

    fun toString(depth: Int = 0): String {
        return "${addSpaces(depth)}- $name (dir, size = ${calculateSize()})\n" +
                directories.joinToString(separator = "") { myDirectory ->
                    myDirectory.toString(depth + 1)
                } +
                files.joinToString(separator = "\n") { file ->
                    addSpaces(depth + 1) + file.toString()
                } +
                "\n"
    }

    private fun addSpaces(depth: Int): String = "\t".repeat(depth)
}