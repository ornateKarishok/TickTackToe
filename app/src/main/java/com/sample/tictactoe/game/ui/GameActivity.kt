package com.sample.tictactoe.game.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sample.tictactoe.databinding.ActivityGameBinding
import com.sample.tictactoe.game.GameLogic
import com.sample.tictactoe.game.viewmodel.GameViewModel
import com.sample.tictactoe.result.ResultActivity
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var vmGame: GameViewModel

    private var boardList = mutableListOf<Button>()

    private val nought = "O"
    private val cross = "X"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
        vmGame = GameViewModel(boardList)
        observeTurn()
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

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        vmGame.addToBoard(view)

        if (GameLogic.checkForVictory(nought, boardList)) {
            result("Noughts Win!")
        } else if (GameLogic.checkForVictory(cross, boardList)) {
            result("Crosses Win!")
        }

        if (vmGame.fullBoard()) {
            result("Draw")
        }
    }

    private fun result(title: String) {
        val nextActivity = Intent(this@GameActivity, ResultActivity::class.java)
        nextActivity.putExtra("Game_score", title)
        startActivity(nextActivity)
        finish()
    }

    private fun observeTurn() {
        lifecycleScope.launch {
            vmGame.turn.collect {
                if (it != "") {
                    binding.turnTV.text = it
                }
            }
        }
    }
}