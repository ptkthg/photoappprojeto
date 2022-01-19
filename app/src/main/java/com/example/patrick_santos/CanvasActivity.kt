package com.example.patrick_santos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CanvasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.canvas_activity)

        val received = intent.extras?.getString(BUNDLE_INPUT_NUMBER_KEY) ?: ""

        val viewCanvas = findViewById<ViewCanvas>(R.id.view_canvas)
        viewCanvas.setNumber(received)
        viewCanvas.invalidate()
    }

    companion object {
        const val BUNDLE_INPUT_NUMBER_KEY = "com.example.patrick_santos.canvas_input_number"
    }
}