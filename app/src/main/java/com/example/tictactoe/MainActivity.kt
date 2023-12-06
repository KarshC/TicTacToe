package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        CIRCLE,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private lateinit var binding: ActivityMainBinding

    private val boardList = mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardClick(view: View) {
        if (view !is Button) {
            return
        }
        addToBoard(view)
    }

    private fun addToBoard(button: Button) {
        if (button.text != "") return

        if (currentTurn == Turn.CIRCLE) {
            button.text = CIRCLE
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.CIRCLE
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (currentTurn == Turn.CIRCLE) turnText =
            "Turn $CIRCLE" else if (currentTurn == Turn.CROSS) turnText = "Turn $CROSS"

        binding.turnText.text = turnText
    }

    companion object {
        const val CIRCLE = "O"
        const val CROSS = "X"
    }
}