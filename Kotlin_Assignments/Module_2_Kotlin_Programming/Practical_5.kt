package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {
    /* Program to Find ASCII value of a character */

    println("<-----------  ASCII Value of a Character ----------->")

    print("Enter a Character for ASCII Value: ")
    val character = readln().toCharArray()[0]

    val ascii_val = character.code

    println("ASCII value of $character : $ascii_val")
}