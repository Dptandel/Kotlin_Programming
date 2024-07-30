package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {
    /* Kotlin Program to Add Two Integers */

    println("<----------- Addition of Two Integers ----------->")

    print("Enter number 1: ")
    val num1: Int = readln().toInt()
    print("Enter number 2: ")
    val num2: Int = readln().toInt()

    println("Addition of $num1 and $num2 : ${num1+num2}")

}