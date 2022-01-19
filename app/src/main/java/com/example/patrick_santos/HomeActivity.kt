package com.example.patrick_santos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.patrick_santos.CanvasActivity.Companion.BUNDLE_INPUT_NUMBER_KEY

class HomeActivity : AppCompatActivity() {

    private lateinit var edtInput: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculate: Button
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        edtInput = findViewById(R.id.edt_input)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate = findViewById(R.id.btn_calculate)
        btnSend = findViewById(R.id.btn_send)
    }

    private fun setupListeners() {
        btnCalculate.setOnClickListener {
            val input = try {
                edtInput.text.toString().toInt()
            } catch (nfe: NumberFormatException) {
                0
            }
            tvResult.text = addFive(input)
        }

        btnSend.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            intent.putExtra(BUNDLE_INPUT_NUMBER_KEY, tvResult.text.toString())
            startActivity(intent)
        }
    }

    external fun addFive(input: Int): String

    companion object {
        init {
            System.loadLibrary("myapplication")
        }
    }
}