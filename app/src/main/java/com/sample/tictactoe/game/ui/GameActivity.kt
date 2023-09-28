package com.sample.tictactoe.game.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sample.tictactoe.databinding.ActivityGameBinding
import com.sample.tictactoe.game.viewmodel.GameViewModel
import com.sample.tictactoe.result.ResultActivity
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var vmGame: GameViewModel

    private var boardList = mutableListOf<Button>()
    private var turn = "Turn X"
    private var isSinglePlayer: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isSinglePlayer = intent.getBooleanExtra("is_single_player", false)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
        vmGame = GameViewModel(boardList, isSinglePlayer)
        observe()
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
        if(isSinglePlayer && turn == "Turn O"){
            return
        }
        vmGame.addToBoard(view)
    }

    private fun observe() {
        lifecycleScope.launch {
            vmGame.turn.collect {
                if (it != "") {
                    binding.turnTV.text = it
                    turn = it
                }
            }
        }

        lifecycleScope.launch {
            vmGame.result.collect {
                if (it != "") {
                    val nextActivity = Intent(this@GameActivity, ResultActivity::class.java)
                    nextActivity.putExtra("game_score", it)
                    nextActivity.putExtra("is_single_player", isSinglePlayer)
                    startActivity(nextActivity)
                    finish()
                }
            }
        }
    }
}