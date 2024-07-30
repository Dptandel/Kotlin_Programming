package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {
    /* Kotlin Program to Compute Quotient and Remainder */

    println("<----------- Compute Quotient and Remainder ----------->")

    print("Enter number 1: ")
    val num1: Double = readln().toDouble()
    print("Enter number 2: ")
    val num2: Double = readln().toDouble()

    println("For Quotient of $num1 and $num2 : ${num1/num2}")
    println("For Remainder of $num1 and $num2 : ${num1%num2}")
}