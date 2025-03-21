package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second1)

        // Get the message passed from MainActivity
        val message = intent.getStringExtra("message")

        // Find the TextView in activity_second.xml and display the message
        val textView: TextView = findViewById(R.id.textView)
        textView.text = message // Display the message in the TextView

        // Button to return data
        val returnButton: Button = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            // Create an Intent to return data
            val returnIntent = Intent()
            returnIntent.putExtra("resultMessage", "Hello back from SecondActivity")
            setResult(RESULT_OK, returnIntent) // Set the result with data
            finish() // Finish the activity and return to MainActivity
        }
    }
}