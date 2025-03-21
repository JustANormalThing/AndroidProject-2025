package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // Define the ActivityResultLauncher
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ActivityResultLauncher
        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.let {
                    val resultMessage = it.getStringExtra("resultMessage")
                    Log.d(TAG, "Received message: $resultMessage")
                }
            }
        }

        // Find the button and set an OnClickListener
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            // Create an Intent to launch SecondActivity and pass data
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message", "Hello from MainActivity") // Send data
            startForResult.launch(intent) // Launch SecondActivity
        }
    }

    // Other lifecycle methods remain the same
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("savedMessage", "This is saved before rotation")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedMessage = savedInstanceState.getString("savedMessage")
        Log.d(TAG, "Restored message: $savedMessage")
    }
}