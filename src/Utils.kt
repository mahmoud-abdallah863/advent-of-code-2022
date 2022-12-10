import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.exp

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


fun readTestInput(): List<String> {
    val firstStackTraceElement = Throwable().stackTrace[1]
    val packageName = firstStackTraceElement.fileName!!.split(".")[0]

    return File("src", "$packageName/test_input.txt")
        .readLines()
}

fun readInput(): List<String> {
    val firstStackTraceElement = Throwable().stackTrace[1]
    val packageName = firstStackTraceElement.fileName!!.split(".")[0]

    return File("src", "$packageName/input.txt")
        .readLines()
}




fun <T: Comparable<T>>assertEquals(value:T, expected: T) {
    if (value != expected) {
        throw Exception("Expected $expected found $value")
    }
}