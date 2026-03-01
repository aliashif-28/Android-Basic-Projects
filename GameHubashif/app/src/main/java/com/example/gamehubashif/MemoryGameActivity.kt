package com.example.gamehubashif

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemoryGameActivity : AppCompatActivity() {

    private lateinit var cards: Array<ImageView>
    private lateinit var levelText: TextView
    private lateinit var nextBtn: Button

    private var level = 1
    private var matchCount = 0

    private fun getImages(): List<Int> {
        return listOf(
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
        ).shuffled()
    }

    private var images = getImages()

    private var firstCard: ImageView? = null
    private var firstImage: Int = -1
    private var lock = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)

        levelText = findViewById(R.id.levelText)
        nextBtn = findViewById(R.id.nextBtn)

        cards = arrayOf(
            findViewById(R.id.c1), findViewById(R.id.c2), findViewById(R.id.c3),
            findViewById(R.id.c4), findViewById(R.id.c5), findViewById(R.id.c6),
            findViewById(R.id.c7), findViewById(R.id.c8), findViewById(R.id.c9),
            findViewById(R.id.c10), findViewById(R.id.c11), findViewById(R.id.c12),
            findViewById(R.id.c13),
            findViewById(R.id.c14), findViewById(R.id.c15), findViewById(R.id.c16)
        )

        setupCards()

        nextBtn.setOnClickListener {
            level++
            levelText.text = "Level $level"
            resetGame()
        }
    }

    private fun setupCards() {
        cards.forEachIndexed { index, card ->
            card.setImageResource(R.drawable.card_back)
            card.visibility = ImageView.VISIBLE
            card.setOnClickListener {
                if (!lock) flipCard(card, images[index])
            }
        }
    }

    private fun flipCard(card: ImageView, image: Int) {
        card.setImageResource(image)

        if (firstCard == null) {
            firstCard = card
            firstImage = image
        } else {
            lock = true
            Handler().postDelayed({
                if (firstImage == image && firstCard != card) {
                    firstCard?.visibility = ImageView.INVISIBLE
                    card.visibility = ImageView.INVISIBLE
                    matchCount++

                    // All matched → show next button
                    if (matchCount == images.size / 2) {
                        nextBtn.visibility = Button.VISIBLE
                    }
                } else {
                    firstCard?.setImageResource(R.drawable.card_back)
                    card.setImageResource(R.drawable.card_back)
                }
                firstCard = null
                lock = false
            }, 700)
        }
    }

    private fun resetGame() {
        matchCount = 0
        images = getImages()
        nextBtn.visibility = Button.GONE
        setupCards()
    }
}