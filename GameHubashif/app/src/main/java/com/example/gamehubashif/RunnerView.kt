package com.example.gamehubashif

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class RunnerView(context: Context) : View(context) {

    private val paint = Paint()
    private val handler = Handler(Looper.getMainLooper())

    private var lane = 1
    private val laneCount = 3

    private var obstacleLane = Random.nextInt(laneCount)
    private var obstacleY = -200f

    private var speed = 40f
    private var score = 0
    private var gameOver = false

    private var startX = 0f

    // ✅ Game loop
    private val gameLoop = object : Runnable {
        override fun run() {
            if (!gameOver) {
                moveObstacle()
                invalidate()
            }
            handler.postDelayed(this, 30)
        }
    }

    init {
        handler.post(gameLoop)
    }

    private fun moveObstacle() {
        if (width == 0) return

        obstacleY += speed

        val laneWidth = width / laneCount

        val playerX = lane * laneWidth + laneWidth / 4f
        val obsX = obstacleLane * laneWidth + laneWidth / 4f

        // ✅ COLLISION CHECK
        if (obstacleY > height - 220 && obstacleY < height - 120) {
            if (lane == obstacleLane) {
                gameOver = true
            }
        }

        // Respawn obstacle
        if (obstacleY > height) {
            obstacleY = -200f
            obstacleLane = Random.nextInt(laneCount)
            score++
            speed += 0.5f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val laneWidth = width / laneCount

        // Road
        paint.color = Color.DKGRAY
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        // Lane lines
        paint.color = Color.WHITE
        paint.strokeWidth = 5f
        for (i in 1 until laneCount) {
            canvas.drawLine((laneWidth * i).toFloat(), 0f, (laneWidth * i).toFloat(), height.toFloat(), paint)
        }

        // Player
        paint.color = Color.GREEN
        val playerX = lane * laneWidth + laneWidth / 4f
        canvas.drawRect(playerX, height - 200f, playerX + laneWidth / 2f, height - 100f, paint)

        // Obstacle
        paint.color = Color.RED
        val obsX = obstacleLane * laneWidth + laneWidth / 4f
        canvas.drawRect(obsX, obstacleY, obsX + laneWidth / 2f, obstacleY + 100f, paint)

        // Score
        paint.color = Color.YELLOW
        paint.textSize = 60f
        canvas.drawText("Score: $score", 50f, 150f, paint)

        // ✅ GAME OVER TEXT
        if (gameOver) {
            paint.color = Color.RED
            paint.textSize = 100f
            canvas.drawText("GAME OVER", width / 4f, height / 2f, paint)

            paint.textSize = 50f
            canvas.drawText("Tap to Restart", width / 3f, height / 2f + 80f, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        // ✅ Restart
        if (gameOver) {
            gameOver = false
            score = 0
            speed = 25f
            obstacleY = -200f
            lane = 1
            invalidate()
            return true
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> startX = event.x
            MotionEvent.ACTION_UP -> {
                val dx = event.x - startX
                if (dx > 100 && lane < laneCount - 1) lane++
                else if (dx < -100 && lane > 0) lane--
            }
        }
        return true
    }
}