package com.example.project1wordle

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var wordToGuess = ""
    var guessNumber = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        findViewById<TextView>(R.id.word).text = wordToGuess
            viewEvents()
    }

    public fun guess() {
        // Find the read-only guess
        val input = findViewById<View>(R.id.input) as EditText
        val guess = if (guessNumber == 1) {
            findViewById<View>(R.id.guess1) as TextView
        } else if (guessNumber == 2) {
            findViewById<View>(R.id.guess2) as TextView
        } else {
            findViewById<View>(R.id.guess3) as TextView
        }
        if (input.length() != 4) {
            Toast.makeText(applicationContext, "Wrong length! Try again!", Toast.LENGTH_SHORT)
                .show()
        } else {
        guess.text = input.text.toString().uppercase()

            //Check the guess
            val check = checkGuess(guess.text.toString())
            //Populate read-only guess check with that value
            val guessCheck = if (guessNumber == 1) {
                findViewById<View>(R.id.guess1check) as TextView
            } else if (guessNumber == 2) {
                findViewById<View>(R.id.guess2check) as TextView
            } else {
                findViewById<View>(R.id.guess3check) as TextView
            }
            guessCheck.text = check


            //If it's matched or after 3 tries show the answer
            if (guessNumber >= 3 || check == "OOOO") {
                findViewById<TextView>(R.id.word).visibility = View.VISIBLE
                findViewById<Button>(R.id.button).isEnabled = false
            }
            if (guessNumber == 1) {
                findViewById<TextView>(R.id.textView7).visibility = View.VISIBLE
                findViewById<TextView>(R.id.textView).visibility = View.VISIBLE
            }

            if (guessNumber == 2) {
                findViewById<TextView>(R.id.textView2).visibility = View.VISIBLE
                findViewById<TextView>(R.id.textView3).visibility = View.VISIBLE
            }

            if (guessNumber == 3) {
                findViewById<TextView>(R.id.textView4).visibility = View.VISIBLE
                findViewById<TextView>(R.id.textView5).visibility = View.VISIBLE
            }
            input.text.clear()
            guessNumber++

        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }

    private fun viewEvents() {

        val guessButton = findViewById<Button>(R.id.button)
        guessButton.setOnClickListener { guess() }

    }
}