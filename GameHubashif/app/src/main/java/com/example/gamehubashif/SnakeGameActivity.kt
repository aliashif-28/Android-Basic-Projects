package com.example.gamehubashif

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SnakeGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake_game)

        val snakeView = findViewById<SnakeView>(R.id.snakeView)
        val restartBtn = findViewById<Button>(R.id.restartBtn)
        val scoreText = findViewById<TextView>(R.id.scoreText)

        snakeView.onScoreUpdate = {
            scoreText.text = "Score: $it"
        }

        snakeView.onGameOver = {
            restartBtn.visibility = Button.VISIBLE
            Toast.makeText(this, "Game Over! ${scoreText.text}", Toast.LENGTH_LONG).show()
        }

        restartBtn.setOnClickListener {
            restartBtn.visibility = Button.GONE
            snakeView.restartGame()
        }
    }
}