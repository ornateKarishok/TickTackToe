package com.sample.tictactoe.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.tictactoe.databinding.ActivityResultBinding
import com.sample.tictactoe.game.ui.GameActivity
import com.sample.tictactoe.menu.MenuActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gameResult = intent.getStringExtra("game_score")
        val isSinglePlayer = intent.getBooleanExtra("is_single_player", false)
        binding.resultTitle.text = gameResult
        binding.continueButton.setOnClickListener {
            val nextActivity = Intent(this, GameActivity::class.java)
            nextActivity.putExtra("is_single_player", isSinglePlayer)
            startActivity(nextActivity)
        }
        binding.exitButton.setOnClickListener {
            val nextActivity = Intent(this, MenuActivity::class.java)
            startActivity(nextActivity)
        }
    }
}