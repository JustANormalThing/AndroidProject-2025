package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var notesTextView: TextView
    private lateinit var themeSwitcherButton: Button

    companion object {
        const val PREFS_NAME = "theme_prefs"
        const val KEY_THEME = "theme"
        const val LIGHT_THEME = "light"
        const val DARK_THEME = "dark"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Load the saved theme before calling setContentView
        when (sharedPreferences.getString(KEY_THEME, LIGHT_THEME)) {
            DARK_THEME -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            LIGHT_THEME -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesTextView = findViewById(R.id.notesTextView)

        startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.let {
                    val note = it.getStringExtra("note")
                    note?.let {
                        // Append the new note to the TextView
                        notesTextView.append("$note\n")
                    }
                }
            }
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startForResult.launch(intent)
        }

        // Initialize themeSwitcherButton properly
        themeSwitcherButton = findViewById(R.id.theme_switch_button)
        setButtonColor() // Set the button color based on the current theme
        themeSwitcherButton.setOnClickListener {
            toggleTheme()
        }
    }

    private fun toggleTheme() {
        val editor = sharedPreferences.edit()
        val currentTheme = sharedPreferences.getString(KEY_THEME, LIGHT_THEME)
        if (currentTheme == LIGHT_THEME) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putString(KEY_THEME, DARK_THEME)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putString(KEY_THEME, LIGHT_THEME)
        }
        editor.apply()
        setButtonColor() // Set the button color after changing the theme
        recreate()  // Recreate the activity to apply the new theme
    }

    private fun setButtonColor() {
        if (sharedPreferences.getString(KEY_THEME, LIGHT_THEME) == DARK_THEME) {
            // Dark theme: set button background to yellow
            themeSwitcherButton.setBackgroundColor(0xFF80808)  // Yellow for dark theme
            themeSwitcherButton.setTextColor(ContextCompat.getColor(this, android.R.color.white)) // Black for text
        } else {
            // Light theme: set button background to gray
            themeSwitcherButton.setBackgroundColor(0xFF80808)  // Gray for light theme
            themeSwitcherButton.setTextColor(ContextCompat.getColor(this, android.R.color.black)) // Black for text
        }
    }

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
}