package com.example.rechner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The HistoryActivity is an activity responsible for displaying the stored calculation history.
 * All it does is create a TextView for each calculation and put it into the vertical linear layout.
 */
class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        btn_back.setOnClickListener {
            run {
                finish()
            }
        }

        History.getHistory().forEach { calculation ->
            run {
                val textView = TextView(this)
                textView.text = calculation
                textView.textSize = 30f
                lnr_historyContainer.addView(textView)
            }
        }
    }
}