package com.example.gamehubashif

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ticBtn = findViewById<ImageButton>(R.id.ticBtn)
        val memoryBtn = findViewById<ImageButton>(R.id.memoryBtn)
        val snakeBtn = findViewById<ImageButton>(R.id.snakeBtn)
        val quizBtn = findViewById<ImageButton>(R.id.quizBtn)
        val runnerBtn = findViewById<ImageButton>(R.id.runnerBtn)

        // TicTacToe open
        ticBtn.setOnClickListener {
            startActivity(Intent(this, TicTacToeActivity::class.java))
        }

        // Memory Game open
        memoryBtn.setOnClickListener {
            startActivity(Intent(this, MemoryGameActivity::class.java))
        }

        // Snake Game open
        snakeBtn.setOnClickListener {
            startActivity(Intent(this, SnakeGameActivity::class.java))
        }

        // ⭐ Quiz Game open
        quizBtn.setOnClickListener {
            startActivity(Intent(this, QuizGameActivity::class.java))
        }

        runnerBtn.setOnClickListener {
            startActivity(Intent(this, RunnerGame::class.java))
        }
    }
}