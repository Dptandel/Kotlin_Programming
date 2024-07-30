package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {
    /* Kotlin Program to Check Whether a Number is Even or Odd */

    println("<----------- EVEN ODD PROGRAM ----------->")

    print("Enter number : ")
    val num: Int = readln().toInt()

    if (num % 2 == 0){
        print("Number is EVEN")
    } else {
        print("Number is ODD")
    }
}