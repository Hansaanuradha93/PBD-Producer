package com.example.hansaanuradha.producer

class PrimeFactors {

    // Lets find prime factors
    fun primeFactors(number: String): List<Int> {
        var n = Integer.parseInt(number)
        val factors = ArrayList<Int>()
        for (i in 2..n) {
            while (n % i == 0) {
                factors.add(i)
                n /= i
            }
        }
        return factors
    }

    fun convertToString(number: String, primeFactors: List<Int>): String {
        var result = ""
        for (fact in primeFactors) {
            result = "$result   $fact,"

        }
        val finalResult = "$number:-\t$result"
        return finalResult
    }




}