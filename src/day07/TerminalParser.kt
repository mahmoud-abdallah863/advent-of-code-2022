package day07

class TerminalParser {

    private var rootDirectory = MyDirectory(name="/")
    private var currentDirectory = rootDirectory

    private var listingDirectoryContent = false

    private val uuidToDirectory = mutableMapOf<String, MyDirectory>(rootDirectory.uuid to rootDirectory)

    fun parse(input: List<String>): MyDirectory {
        input.forEach { line ->
            if (line.startsWith("$")) {
                parseCommand(line)
            }else {
                parseFileOrDirectory(line)
            }
        }
        return rootDirectory
    }

    private fun parseCommand(line: String) {
        val splittedLine = line.split(" ")
        val command = splittedLine[1]
        when(command) {
            "cd" -> {
                val dirName = splittedLine[2]
                if (dirName == "/" && currentDirectory.isRootDirectory()) return

                currentDirectory = if (dirName == "..") {
                    if (currentDirectory.parentDirUUID == null) {
                        throw Exception("${currentDirectory.name} does not have a parent directory")
                    }
                    uuidToDirectory[currentDirectory.parentDirUUID]!!
                }else {
                    val directoryIndex = currentDirectory.directories.indexOfFirst { it.name == dirName }
                    if (directoryIndex == -1) {
                        throw Exception("Can't find a directory named $dirName in ${currentDirectory.name}")
                    }
                    currentDirectory.directories[directoryIndex]

                }
                listingDirectoryContent = false
            }
            "ls" -> {
                listingDirectoryContent = true
            }
            else -> {}
        }
    }

    private fun parseFileOrDirectory(line: String) {
        if (!listingDirectoryContent) {
            throw Exception("Not listing directory content!!")
        }

        val (s1, s2) = line.split(" ")
        if (s1.startsWith("dir")) {
            val newDirectory = MyDirectory(name = s2, parentDirUUID = currentDirectory.uuid)
            uuidToDirectory[newDirectory.uuid] = newDirectory
            currentDirectory.directories.add(newDirectory)
        }else {
            val newFile = MyFile(name = s2, size = s1.toInt())
            currentDirectory.files.add(newFile)
        }
    }
}