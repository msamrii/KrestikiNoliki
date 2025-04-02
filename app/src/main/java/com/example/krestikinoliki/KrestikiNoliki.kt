package com.example.krestikinoliki

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KrestikiNoliki : AppCompatActivity() {

    private var currentPlayerSymbol = "X"
    private var currentPlayerName = "Крестики"
    private val board = Array(3) { arrayOfNulls<String>(3) }
    private val buttons = ArrayList<Button>()
    private var moveCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_krestikinoliki)

        for (i in 0..8) {
            val resID = resources.getIdentifier("button$i", "id", packageName)
            val btn = findViewById<Button>(resID)
            buttons.add(btn)
            val row = i / 3
            val col = i % 3
            btn.setOnClickListener {
                if (btn.text.isEmpty()) {
                    btn.text = currentPlayerSymbol
                    board[row][col] = currentPlayerSymbol
                    moveCount++

                    if (checkWin()) {
                        Toast.makeText(this, "$currentPlayerName победили!", Toast.LENGTH_LONG).show()
                        resetBoard()
                    } else if (moveCount == 9) {
                        Toast.makeText(this, "Ничья!", Toast.LENGTH_LONG).show()
                        resetBoard()
                    } else {
                        switchPlayer()
                    }
                }
            }
        }
    }

    private fun switchPlayer() {
        if (currentPlayerSymbol == "X") {
            currentPlayerSymbol = "O"
            currentPlayerName = "Нолики"
        } else {
            currentPlayerSymbol = "X"
            currentPlayerName = "Крестики"
        }
    }

    private fun checkWin(): Boolean {
        for (i in 0..2) {
            if (board[i][0] == currentPlayerSymbol &&
                board[i][1] == currentPlayerSymbol &&
                board[i][2] == currentPlayerSymbol
            ) return true
            if (board[0][i] == currentPlayerSymbol &&
                board[1][i] == currentPlayerSymbol &&
                board[2][i] == currentPlayerSymbol
            ) return true
        }
        if (board[0][0] == currentPlayerSymbol &&
            board[1][1] == currentPlayerSymbol &&
            board[2][2] == currentPlayerSymbol
        ) return true
        if (board[0][2] == currentPlayerSymbol &&
            board[1][1] == currentPlayerSymbol &&
            board[2][0] == currentPlayerSymbol
        ) return true
        return false
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = null
            }
        }
        for (btn in buttons) {
            btn.text = ""
        }
        currentPlayerSymbol = "X"
        currentPlayerName = "Крестики"
        moveCount = 0
    }
}