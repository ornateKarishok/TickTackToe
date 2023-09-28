package com.sample.tictactoe.game.viewmodel

import android.widget.Button
import androidx.lifecycle.ViewModel
import com.sample.tictactoe.game.Turn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(private val boardList: List<Button>) : ViewModel() {
    private var currentTurn = Turn.CROSS
    private val nought = "O"
    private val cross = "X"

    private var _turn = MutableStateFlow(String())
    var turn: StateFlow<String> = _turn

    fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "")
                return false
        }
        return true
    }

    fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if (currentTurn == Turn.NOUGHT) {
            button.text = nought
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = cross
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (currentTurn == Turn.CROSS)
            turnText = "Turn $cross"
        else if (currentTurn == Turn.NOUGHT)
            turnText = "Turn $nought"

        _turn.value = turnText
    }
}