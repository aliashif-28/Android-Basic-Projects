package com.example.gamehubashif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RunnerGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IMPORTANT: direct custom view
        setContentView(RunnerView(this))
    }
}


