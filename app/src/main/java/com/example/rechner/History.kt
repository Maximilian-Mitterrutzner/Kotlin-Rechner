package com.example.rechner


/**
 * The History object is responsible for providing a central access to the calculation history
 * for all other activities.
 */
object History {
    private val historyItems = ArrayList<String>()

    fun append(calculation: String) {
        historyItems.add(calculation)
    }

    fun getHistory(): ArrayList<String> {
        return historyItems
    }
}