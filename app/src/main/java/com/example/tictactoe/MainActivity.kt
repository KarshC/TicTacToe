package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        CIRCLE,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossScore = 0
    private var circleScore = 0


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

    private fun checkForVictory(s: String): Boolean {
        if(match(binding.a1, s) && match(binding.b1, s) && match(binding.c1,s))return true
        if(match(binding.a2, s) && match(binding.b2, s) && match(binding.c2,s))return true
        if(match(binding.a3, s) && match(binding.b3, s) && match(binding.c3,s))return true
        if(match(binding.a1, s) && match(binding.a2, s) && match(binding.a3,s))return true
        if(match(binding.b1, s) && match(binding.b2, s) && match(binding.b3,s))return true
        if(match(binding.c1, s) && match(binding.c2, s) && match(binding.c3,s))return true
        if(match(binding.a1, s) && match(binding.b2, s) && match(binding.c3,s))return true
        if(match(binding.a3, s) && match(binding.b2, s) && match(binding.c1,s))return true
        return false
    }

    private fun match(button: Button, symbol: String) = button.text == symbol

    fun boardClick(view: View) {
        if (view !is Button) {
            return
        }
        addToBoard(view)

        if(checkForVictory(CROSS)){
            crossScore++
            result("Cross Wins!!!")
        }

        if(checkForVictory(CIRCLE)){
            circleScore++
            result("Circle Wins!!!")
        }

        if (isFullBoard()) {
            result("Draw")
        }
    }

    private fun result(title: String) {
        val message = "\nCross Wins - $crossScore\n\nCircle Wins - $circleScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset") { _, _ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList) {
            button.text = ""
        }

        if (firstTurn == Turn.CROSS) firstTurn =
            Turn.CIRCLE else if (firstTurn == Turn.CIRCLE) firstTurn = Turn.CROSS

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun isFullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "") return false
        }
        return true
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