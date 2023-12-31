package com.sample.tictactoe.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sample.tictactoe.R
import com.sample.tictactoe.databinding.ActivityMenuBinding
import com.sample.tictactoe.game.ui.GameActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(
            this,
            backPressedCallback
        )

        binding.startGameButton.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder
                .setTitle(resources.getString(R.string.play_with_bot_question))
                .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                    val nextActivity = Intent(this, GameActivity::class.java)
                    nextActivity.putExtra("is_single_player", true)
                    startActivity(nextActivity)
                    finish()
                }
                .setNegativeButton(
                    resources.getString(R.string.no)
                ) { _, _ ->
                    val nextActivity = Intent(this, GameActivity::class.java)
                    nextActivity.putExtra("is_single_player", false)
                    startActivity(nextActivity)
                    finish()
                }
            builder.create()
            builder.show()


        }
        binding.exit.setOnClickListener {
            closeApp()
        }
    }

    private fun closeApp() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(resources.getString(R.string.are_you_sure_you_want_to_exit))
            .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                finish()
            }
            .setNegativeButton(
                resources.getString(R.string.no)
            ) { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            closeApp()
        }
    }
}