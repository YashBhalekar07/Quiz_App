package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    var score:Int=0
    lateinit var binding:ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score=intent.getIntExtra("Score",0)

        binding.apply {
            scoreTxt.text=score.toString()
            backBtn.setOnClickListener {
                startActivity(Intent(this@ScoreActivity,MainActivity::class.java))
                finish()
            }
        }





    }
}