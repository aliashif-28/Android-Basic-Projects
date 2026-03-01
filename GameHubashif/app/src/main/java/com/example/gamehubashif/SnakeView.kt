package com.example.gamehubashif

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.random.Random

class SnakeView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    var onGameOver: (() -> Unit)? = null
    var onScoreUpdate: ((Int) -> Unit)? = null

    private val paint = Paint()
    private val snake = mutableListOf<Pair<Int, Int>>()
    private var direction = "RIGHT"
    private var food = Pair(5, 5)
    private val size = 40
    private val handler = Handler()
    private var gameRunning = true
    private var score = 0

    private var startX = 0f
    private var startY = 0f

    init {
        snake.add(Pair(10, 10))
        gameLoop()
    }

    private fun gameLoop() {
        handler.postDelayed({
            if (!gameRunning) return@postDelayed
            moveSnake()
            invalidate()
            gameLoop()
        }, 250)
    }

    private fun moveSnake() {
        val head = snake.first()
        val newHead = when (direction) {
            "UP" -> Pair(head.first, head.second - 1)
            "DOWN" -> Pair(head.first, head.second + 1)
            "LEFT" -> Pair(head.first - 1, head.second)
            else -> Pair(head.first + 1, head.second)
        }

        // ⭐ Game Over (Wall hit)
        if (newHead.first < 0 || newHead.second < 0 ||
            newHead.first >= width / size || newHead.second >= height / size) {

            gameRunning = false
            handler.removeCallbacksAndMessages(null)
            onGameOver?.invoke()
            return
        }

        snake.add(0, newHead)

        if (newHead == food) {
            score++
            onScoreUpdate?.invoke(score)

            food = Pair(Random.nextInt(width / size), Random.nextInt(height / size))
        } else {
            snake.removeAt(snake.lastIndex)
        }
    }

    fun restartGame() {
        snake.clear()
        snake.add(Pair(10, 10))
        direction = "RIGHT"
        score = 0
        onScoreUpdate?.invoke(score)

        gameRunning = true
        handler.removeCallbacksAndMessages(null)
        gameLoop()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!gameRunning) return true

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val dx = event.x - startX
                val dy = event.y - startY

                if (abs(dx) > abs(dy)) {
                    if (dx > 0 && direction != "LEFT") direction = "RIGHT"
                    else if (dx < 0 && direction != "RIGHT") direction = "LEFT"
                } else {
                    if (dy > 0 && direction != "UP") direction = "DOWN"
                    else if (dy < 0 && direction != "DOWN") direction = "UP"
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.GREEN
        snake.forEach {
            canvas.drawRect(
                it.first * size.toFloat(),
                it.second * size.toFloat(),
                (it.first + 1) * size.toFloat(),
                (it.second + 1) * size.toFloat(),
                paint
            )
        }

        paint.color = Color.RED
        canvas.drawRect(
            food.first * size.toFloat(),
            food.second * size.toFloat(),
            (food.first + 1) * size.toFloat(),
            (food.second + 1) * size.toFloat(),
            paint
        )
    }
}