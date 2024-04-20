package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.quizapp.Adapter.QuestionAdapter
import com.example.quizapp.Domain.QuestionModel
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(),QuestionAdapter.score {
    private lateinit var binding: ActivityQuestionBinding
    var position:Int=0
    var receivedList : MutableList<QuestionModel> = mutableListOf()
    var allScore=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this@QuestionActivity.window
        window.statusBarColor = ContextCompat.getColor(this@QuestionActivity, R.color.grey)



        receivedList=intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply {
            backBtn.setOnClickListener{finish()}
            progressBar.progress = 1
            questionTxt.text=receivedList[position].question
            val drawableResourceId:Int = binding.root.resources.getIdentifier(
                receivedList[position].picPath,
                "drawable",binding.root.context.packageName
            )

            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)//2:07:18
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(pic)

            loadAnswers()

            rightArrow.setOnClickListener{
                if(progressBar.progress==10){
                    val intent = Intent(this@QuestionActivity,ScoreActivity::class.java)
                    intent.putExtra("Score",allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }
                position++
                progressBar.progress = progressBar.progress+1 //2:14:50
                questionNumber.text="Question"+progressBar.progress+"/10"
                questionTxt.text=receivedList[position].question

                val drawableResourceId : Int=binding.root.resources.getIdentifier(
                    receivedList[position].picPath,
                    "drwable",binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                loadAnswers()

            }

            leftArrow.setOnClickListener {
                if(progressBar.progress==1){
                    return@setOnClickListener
                }

                position--

                progressBar.progress = progressBar.progress-1 //2:14:50
                questionNumber.text="Question"+progressBar.progress+"/10"
                questionTxt.text=receivedList[position].question

                val drawableResourceId : Int=binding.root.resources.getIdentifier(
                    receivedList[position].picPath,
                    "drwable",binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                loadAnswers()
            }

        }
    }

    private fun loadAnswers(){
        val users:MutableList<String> = mutableListOf()
        users.add(receivedList[position].answer_1.toString())
        users.add(receivedList[position].answer_2.toString())
        users.add(receivedList[position].answer_3.toString())
        users.add(receivedList[position].answer_4.toString())

        if(receivedList[position].clickedAnswer!=null)users.add(receivedList[position].clickedAnswer.toString())

        val questionAdapter by lazy {
            QuestionAdapter(
                receivedList[position].correctAnswer.toString(),users,this
            )
        }

        questionAdapter.differ.submitList(users)
        binding.questionList.apply {
            layoutManager=LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }

    }

    override fun amount(number: Int, clickedAnswer: String) {
        allScore+=number
        receivedList[position].clickedAnswer=clickedAnswer
    }
}