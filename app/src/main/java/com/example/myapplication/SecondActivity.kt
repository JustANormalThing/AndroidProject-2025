package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second1)

        val noteEditText: EditText = findViewById(R.id.noteEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val note = noteEditText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("note", note)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Close the SecondActivity1
        }
    }
}