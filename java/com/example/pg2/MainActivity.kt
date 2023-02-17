//Brenan Marenger
//Andriod Programming: PG2
//Date: 2/12/2023
//This android app runs wordle, keeping track of the number of guesses, displaying progress, and checking validity of the guess
//the letter will appear uppercase if the letter is in the right spot or lower case if the letter is in the word, but in the wrong place
package com.example.pg2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val wordle = WordleStuff()
    var secretWord = wordle.randword()

    var submitButton: Button? = null
    var guess: EditText? = null
    var feedback: TextView? = null

    var numGuesses: Int = 0
    var displayProgress: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById (R.id.guessBtn)
        feedback = findViewById (R.id.feedback)
        displayProgress = findViewById (R.id.progress)

        submitButton!!.setOnClickListener {
            guess = findViewById(R.id.guessWord)
            var guessString:String = guess!!.getText().toString().uppercase()

            println("SECRET: ${secretWord}")

            if(guessString.length == 5 && wordle.WordleWords.contains(guessString)){//if is a valid word
                var progress = charArrayOf ('_','_','_','_','_')
                numGuesses++
                for (i in 0..4) { //iterate through guessed word
                        var index = secretWord.indexOf(guessString[i])//finds the first index of the letter
                        while(index != -1){ //find each index of the current letter
                            if(guessString[index] == secretWord[index]){ //if the char match at the index
                                progress[index] = guessString[index].uppercaseChar()
                            } else {
                                if(!progress[i].isUpperCase()){
                                    progress[i] = guessString[i].lowercaseChar()
                                }
                            }
                            index = secretWord.indexOf(guessString[i], index+1)
                        }
                }
                displayProgress!!.text = String(progress)
                if(String(progress) == secretWord?.uppercase()){
                    feedback!!.text = "Winner! You got it in ${numGuesses} guesses. "
                    //remove button?
                } else {
                    feedback!!.text = "Guess another word"
                }
            } else {
                feedback!!.text = "Invalid input!"
            }
        }
    }
}
