package day07

data class MyFile (
    val name: String,
    val size: Int
) {
    override fun toString(): String {
        return "- $name (file, size=$size)"
    }
}