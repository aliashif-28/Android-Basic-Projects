package com.example.gamehubashif

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    private lateinit var scoreText: TextView
    private lateinit var resetBtn: Button

    private var player = "X"
    private var board = Array(9) { "" }

    private var xScore = 0
    private var oScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        scoreText = findViewById(R.id.scoreText)
        resetBtn = findViewById(R.id.resetBtn)

        buttons = arrayOf(
            findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3),
            findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6),
            findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)
        )

        buttons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                if (board[index].isEmpty()) {
                    board[index] = player
                    btn.text = player

                    if (checkWinner()) {
                        if (player == "X") xScore++ else oScore++
                        updateScore()

                        Toast.makeText(this, "Player $player Wins!", Toast.LENGTH_LONG).show()
                        resetBoard()
                    } else if (board.all { it.isNotEmpty() }) {
                        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show()
                        resetBoard()
                    } else {
                        player = if (player == "X") "O" else "X"
                    }
                }
            }
        }

        resetBtn.setOnClickListener {
            xScore = 0
            oScore = 0
            updateScore()
            resetBoard()
        }
    }

    private fun checkWinner(): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),
            intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8),
            intArrayOf(0,4,8), intArrayOf(2,4,6)
        )

        for (pos in winPositions) {
            if (board[pos[0]] == player &&
                board[pos[1]] == player &&
                board[pos[2]] == player) {
                return true
            }
        }
        return false
    }

    private fun resetBoard() {
        board = Array(9) { "" }
        buttons.forEach { it.text = "" }
        player = "X"
    }

    private fun updateScore() {
        scoreText.text = "X: $xScore   O: $oScore"
    }
}