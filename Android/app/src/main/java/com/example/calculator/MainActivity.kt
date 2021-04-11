package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {
        private val INPUT_BUTTONS = listOf(
                listOf("1", "2", "3", "/"),
                listOf("4", "5", "6", "*"),
                listOf("7", "8", "9", "-"),
                listOf("0", ".", "=", "+")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCells(findViewById(R.id.calculator_input_container_line1), 0)
        addCells(findViewById(R.id.calculator_input_container_line2), 1)
        addCells(findViewById(R.id.calculator_input_container_line3), 2)
        addCells(findViewById(R.id.calculator_input_container_line4), 3)
    }

    private fun addCells(linearLayout: LinearLayout, position: Int) {
        for (x in INPUT_BUTTONS[position].indices) {
            linearLayout.addView(
                    TextView(
                            ContextThemeWrapper(this, R.style.CalculatorInputButton)
                    ).apply {
                        text = INPUT_BUTTONS[position][x]
                        setOnClickListener { onCellClicked(this.text.toString()) }
                    },
                    LinearLayout.LayoutParams(
                            0,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            1f
                    )
            )
        }
    }


    private var input: Int? = null

    private fun onCellClicked(value: String) {
        when {
            value.isNum() -> {
                input = value.toInt()
                updateDisplayContainer(value)
            }
        }
    }
        private fun updateDisplayContainer(value: Any) {
            findViewById<TextView>(R.id.calculator_display_container).text = value.toString()
        }


    fun String.isNum(): Boolean {
        return length == 1 && isDigitsOnly()
    }

