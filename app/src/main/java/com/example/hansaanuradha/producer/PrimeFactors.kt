package com.example.hansaanuradha.producer

class PrimeFactors {

    // Lets find prime factors
    fun findPrimeFactors(number: String): String {
        var n = Integer.parseInt(number)
        val factors = ArrayList<Int>()
        for (i in 2..n) {
            while (n % i == 0) {
                factors.add(i)
                n /= i
            }
        }

        val finalResult = convertToString(number, factors)
        return finalResult
    }


    fun convertToString(number: String, primeFactors: List<Int>): String {
        var result = ""
        if (number.equals("0")) {
            result = "No Prime Factors"
        } else {
            for (fact in primeFactors) {
                result = "$result   $fact,"

            }
        }
        val finalResult = "$number:-\t$result"
        return finalResult
    }




}