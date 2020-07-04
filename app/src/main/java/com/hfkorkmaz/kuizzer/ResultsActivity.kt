package com.hfkorkmaz.kuizzer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val intent = intent
        val numberOfQuestionsAnsweredCorrectly =intent.getIntExtra("numberOfQuestionsAnsweredCorrectly", 0)
        val amountOfQuestions = intent.getIntExtra("amount", 10)
        result_text.text = "You answered "+numberOfQuestionsAnsweredCorrectly+" questions out of "+amountOfQuestions+" correctly."
    }

    fun goHome(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}