package com.example.hansaanuradha.producer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Lets try to get the intent object
        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }



    }

    private fun handleSendText(intent: Intent) {

        // Lets handle Single Number
        intent.getStringExtra("SingleNumber")?.let {
            // Update UI to reflect text being shared
            val value = intent.getStringExtra("SingleNumber")
            // Print the value coming from the Consumer application
            Log.v("intent", value.toString())

            // Lets try to find the prime factors
            val primeFactor = PrimeFactors()
            var result: String = primeFactor.findPrimeFactors(value)

            // Lets pass the result to the Consumer app
            sendIntent(result)
        }


        // Lets handle Multiple Numbers
        intent.getStringExtra("MultipleNumbers")?.let {
            // Update UI to reflect text being shared
            val value = intent.getStringExtra("MultipleNumbers")

            // Print the value coming from the Consumer application
            Log.v("intent", value.toString())

            // Create arrays list
            var numbersInString = ArrayList<String>()

            // Split the values and create a string array
            val values = value.split(", ")
            val numberOfIndexes = values.size - 1

            // Lets find prime factors now
            val primeFactor = PrimeFactors()

            var finalResult = ""
            var result: String

            // Add those values to a String array list
            for (i in 0..numberOfIndexes) {
                numbersInString.add(values.get(i))

                // Find prime factors
                result = primeFactor.findPrimeFactors(numbersInString.get(i))

                finalResult = "$finalResult\n$result\n"

            }

            // Lets print the final result
            Log.v("final_result", finalResult)

            // Lets pass the result to the Consumer app
            sendIntent(finalResult)
        }
    }

    // Send a new intent to an application
    private fun sendIntent(result: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, result)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))

    }


}
