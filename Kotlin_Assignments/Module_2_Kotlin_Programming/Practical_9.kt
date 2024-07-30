package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {

    /* Kotlin Program to find max value using function as express */

    println("<----------- MAX VALUE IN LIST ----------->")

    val listOfNumber = listOf(10, 50, 60, 40, 90, 20, 30, 80, 70)

    var maxValue = listOfNumber[0]

    for (num in listOfNumber) {
        if (num > maxValue) {
            maxValue = num
        }
    }

    println("Maximum value in the list: $maxValue")

}
