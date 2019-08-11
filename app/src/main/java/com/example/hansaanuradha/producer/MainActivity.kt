package com.example.hansaanuradha.producer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.ArrayList

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
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            // Update UI to reflect text being shared
            val value = intent.getStringExtra(Intent.EXTRA_TEXT)
            Log.v("intent", value.toString())

            // Lets try to find the prime factors
            val primeFactor = PrimeFactors()
            var primeFactors: List<Int> = primeFactor.primeFactors(value)
            var result: String = primeFactor.convertToString(primeFactors)

            // Lets pass the result to the Consumer app
            sendIntent(result)
        }
    }

    private fun sendIntent(result: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.putIntegerArrayListExtra(Intent.EXTRA_TEXT, result)
        sendIntent.putExtra(Intent.EXTRA_TEXT, result)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
    }


}
