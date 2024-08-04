package KotlinAssignment.Module_2_Kotlin_Programming

fun main() {

    /* Kotlin Program to create map instance and store values with State, City pair.
       Also print all value using loop. (Hashmap) */

    val stateCityMap = hashMapOf<String, String>()

    stateCityMap["California"] = "Los Angeles"
    stateCityMap["New York"] = "New York City"
    stateCityMap["Texas"] = "Houston"
    stateCityMap["Florida"] = "Miami"
    stateCityMap["Illinois"] = "Chicago"

    println("State and City Pairs:")
    for ((state, city) in stateCityMap) {
        println("State: $state - City: $city")
    }
}