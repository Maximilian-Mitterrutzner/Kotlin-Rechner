package com.example.rechner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is the main activity.
 *
 * Here all the calculation logic resides. Specifically, UI elements are being monitored for
 * changes which then trigger a recalculation (and an entry in the history if the user wants).
 *
 * Contrary to the request, calculations are only performed once either the desired operation is
 * changed or inputted numbers are submitted. If calculations were performed every time the input
 * is valid, that would mean for example that 5 history entries would be created if one were to
 * enter a five digit number, thus cluttering the history quite quickly.
 *
 * The logic could be moved to a different class, but considering it is tightly coupled with the UI
 * and the code is fairly short, that would be unnecessary.
 */
class MainActivity : AppCompatActivity() {
    var operation: Char = '+'
    var recordHistory: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RadioButtonGroup(rdb_add, rdb_sub, rdb_mul, rdb_div)

        addListeners()
    }

    fun update() {
        if(num_one.text.isBlank() || num_two.text.isBlank()) {
            return
        }

        val numOne = num_one.text.toString().toDouble()
        val numTwo = num_two.text.toString().toDouble()

        val result: Double = when (operation) {
            '+' -> numOne + numTwo
            '-' -> numOne - numTwo
            '*' -> numOne * numTwo
            '/' -> numOne / numTwo
            else -> 0.0
        }

        txt_result.text = result.toString()

        if(recordHistory) {
            History.append(num_one.text.toString() + " " + operation + " " + num_two.text.toString() + " = " + result.toString())
        }
    }

    fun addListeners() {
        rdb_add.setOnClickListener {
            operation = '+'
            update()
        }

        rdb_sub.setOnClickListener {
            operation = '-'
            update()
        }

        rdb_mul.setOnClickListener {
            operation = '*'
            update()
        }

        rdb_div.setOnClickListener {
            operation = '/'
            update()
        }

        val listener = object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    update()
                    return  true
                }
                return false
            }
        }
        num_one.setOnEditorActionListener(listener)
        num_two.setOnEditorActionListener(listener)

        swt_history.setOnCheckedChangeListener { _, isChecked ->
            run {
                recordHistory = isChecked
            }
        }

        btn_showHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}