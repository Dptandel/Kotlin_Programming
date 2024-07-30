package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {
    /* Kotlin Program to Find the Frequency of Character in a String */

    println("<----------- FREQUENCY OF CHARACTER IN STRING ----------->")

    print("Enter the String: ")
    val str = readln().toLowerCase()
    val charFrequency = IntArray(256)  // Assuming ASCII characters

    // Count frequency of each character in the string
    for (ch in str) {
        if (ch.isLetterOrDigit()) {  // consider only letters and digits
            charFrequency[ch.toInt()]++
        }
    }

    // Print the frequency of each character
    println("Character frequencies:")
    for (i in 0..<256) {
        if (charFrequency[i] > 0) {
            println("${i.toChar()} : ${charFrequency[i]}")
        }
    }
}