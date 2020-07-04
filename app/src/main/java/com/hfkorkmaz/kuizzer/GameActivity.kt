package com.hfkorkmaz.kuizzer

import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beust.klaxon.Klaxon
import com.hfkorkmaz.kuizzer.adapters.CategoriesAdapter
import com.hfkorkmaz.kuizzer.adapters.QuizOptionsAdapter
import com.hfkorkmaz.kuizzer.models.*
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_game.progressBar
import java.lang.ref.WeakReference
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    var quizData: QuizFormattedData? = null
    var adapter: QuizOptionsAdapter? = null
    var currentQuestion: Int = 0
    var numberOfQuestionsAnsweredCorrectly: Int = 0
    var answered: Boolean = false

    var runnable = Runnable { }
    var handler = Handler()
    val time: Long = 15000
    val amount: Int = 10
    fun onTimeRunOut() {
        Toast.makeText(
            applicationContext,
            "Time ran out",
            Toast.LENGTH_SHORT
        ).show()
        if (quizData!!.results!![currentQuestion]!!.options!![0].isCorrect!!) {
            option_text1.setTextColor(Color.parseColor("#00CC00"))

        }
        if (quizData!!.results!![currentQuestion]!!.options!![1].isCorrect!!) {
            option_text2.setTextColor(Color.parseColor("#00CC00"))

        }
        if (quizData!!.results!![currentQuestion]!!.options!![2].isCorrect!!) {
            option_text3.setTextColor(Color.parseColor("#00CC00"))
        }
        if (quizData!!.results!![currentQuestion]!!.options!![3].isCorrect!!) {
            option_text4.setTextColor(Color.parseColor("#00CC00"))
        }
        answered = true
        if (quizData?.results?.size == currentQuestion + 1) {
            btn_finish.visibility = View.VISIBLE
        } else {
            btn_next_question.visibility = View.VISIBLE
        }
    }

    val timer = object : CountDownTimer(time, 1000) {


        override fun onFinish() {
            onTimeRunOut()
        }

        override fun onTick(millisUntilFinished: Long) {
            time_text.text = "" + millisUntilFinished / 1000 + " Seconds"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val intent = intent

        val task = GetQuizDataTask(this, amount, intent.getIntExtra("categoryId", 10))
        task.execute()

    }

    fun onFistOptionClick(view: View) {
        if (answered) return
        Toast.makeText(
            applicationContext,
            quizData!!.results!![currentQuestion]!!.options[0]!!.isCorrect!!.toString(),
            Toast.LENGTH_SHORT
        ).show()
        if (quizData!!.results!![currentQuestion]!!.options[0]!!.isCorrect!!) {
            option_text1.setTextColor(Color.parseColor("#00CC00"))
            numberOfQuestionsAnsweredCorrectly++
        } else {
            option_text1.setTextColor(Color.parseColor("#FF0000"))
            if (quizData!!.results!![currentQuestion]!!.options!![1].isCorrect!!) {
                option_text2.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![2].isCorrect!!) {
                option_text3.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![3].isCorrect!!) {
                option_text4.setTextColor(Color.parseColor("#00CC00"))
            }
        }
        answered = true
        timer.cancel()
        if (quizData?.results?.size == currentQuestion + 1) {
            btn_finish.visibility = View.VISIBLE
        } else {
            btn_next_question.visibility = View.VISIBLE
        }

    }

    fun onSecondOptionClick(view: View) {
        if (answered) {
            return
        }
        Toast.makeText(
            applicationContext,
            quizData!!.results!![currentQuestion]!!.options[1]!!.isCorrect!!.toString(),
            Toast.LENGTH_SHORT
        ).show()
        if (quizData!!.results!![currentQuestion]!!.options[1]!!.isCorrect!!) {
            option_text2.setTextColor(Color.parseColor("#00CC00"))
            numberOfQuestionsAnsweredCorrectly++
        } else {
            option_text2.setTextColor(Color.parseColor("#FF0000"))
            if (quizData!!.results!![currentQuestion]!!.options!![0].isCorrect!!) {
                option_text1.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![2].isCorrect!!) {
                option_text3.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![3].isCorrect!!) {
                option_text4.setTextColor(Color.parseColor("#00CC00"))
            }
        }
        answered = true
        if (quizData?.results?.size == currentQuestion + 1) {
            btn_finish.visibility = View.VISIBLE
        } else {
            btn_next_question.visibility = View.VISIBLE
        }

    }


    fun onThirdOptionClick(view: View) {
        if (answered) return
        Toast.makeText(
            applicationContext,
            quizData!!.results!![currentQuestion]!!.options[2]!!.isCorrect!!.toString(),
            Toast.LENGTH_SHORT
        ).show()
        if (quizData!!.results!![currentQuestion]!!.options[2]!!.isCorrect!!) {
            option_text3.setTextColor(Color.parseColor("#00CC00"))
            numberOfQuestionsAnsweredCorrectly++
        } else {
            option_text3.setTextColor(Color.parseColor("#FF0000"))
            if (quizData!!.results!![currentQuestion]!!.options!![0].isCorrect!!) {
                option_text1.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![1].isCorrect!!) {
                option_text2.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![3].isCorrect!!) {
                option_text4.setTextColor(Color.parseColor("#00CC00"))
            }
        }
        answered = true
        if (quizData?.results?.size == currentQuestion + 1) {
            btn_finish.visibility = View.VISIBLE
        } else {
            btn_next_question.visibility = View.VISIBLE
        }
    }

    fun onFourthOptionClick(view: View) {
        if (answered) return

        Toast.makeText(
            applicationContext,
            quizData!!.results!![currentQuestion]!!.options[3]!!.isCorrect!!.toString(),
            Toast.LENGTH_SHORT
        ).show()
        if (quizData!!.results!![currentQuestion]!!.options[3]!!.isCorrect!!) {
            option_text4.setTextColor(Color.parseColor("#00CC00"))
            numberOfQuestionsAnsweredCorrectly++
        } else {
            option_text4.setTextColor(Color.parseColor("#FF0000"))
            if (quizData!!.results!![currentQuestion]!!.options!![0].isCorrect!!) {
                option_text1.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![1].isCorrect!!) {
                option_text2.setTextColor(Color.parseColor("#00CC00"))

            }
            if (quizData!!.results!![currentQuestion]!!.options!![2].isCorrect!!) {
                option_text3.setTextColor(Color.parseColor("#00CC00"))
            }
        }
        answered = true
        if (quizData?.results?.size == currentQuestion + 1) {
            btn_finish.visibility = View.VISIBLE
        } else {
            btn_next_question.visibility = View.VISIBLE
        }
    }


    fun displayNextQuestion(view: View) {
        currentQuestion++
        question.text =
            quizData?.results?.get(currentQuestion)?.question


        option_text1.text =
            "   " + quizData?.results?.get(currentQuestion)?.options?.get(0)?.text
        option_text2.text =
            "   " + quizData?.results?.get(currentQuestion)?.options?.get(1)?.text
        option_text3.text =
            "   " + quizData?.results?.get(currentQuestion)?.options?.get(2)?.text
        option_text4.text =
            "   " + quizData?.results?.get(currentQuestion)?.options?.get(3)?.text

        option_text1.setTextColor(Color.parseColor("#756F6F"))
        option_text2.setTextColor(Color.parseColor("#756F6F"))
        option_text3.setTextColor(Color.parseColor("#756F6F"))
        option_text4.setTextColor(Color.parseColor("#756F6F"))

        answered = false
        btn_next_question.visibility = View.GONE
        time_text.text = "15 Seconds"
        timer.start()
    }

    fun finishQuiz(view: View) {
        val intent = Intent(applicationContext, ResultsActivity::class.java)
        intent.putExtra("numberOfQuestionsAnsweredCorrectly", numberOfQuestionsAnsweredCorrectly)
        intent.putExtra("amount", amount)
        startActivity(intent)
        finish()
    }

    companion object {
        class GetQuizDataTask internal constructor(
            context: GameActivity,
            amount: Int,
            categoryId: Int
        ) : AsyncTask<Int, String, String?>() {
            private val amount = amount
            private val categoryId = categoryId
            private var resp: String? = null
            private val activityReference: WeakReference<GameActivity> =
                WeakReference(context)

            override fun onPreExecute() {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                activity.progressBar.visibility = View.VISIBLE
            }

            override fun doInBackground(vararg params: Int?): String? {

                try {
                    val quizDataJson =
                        URL("https://opentdb.com/api.php?amount=" + amount + "&category=" + categoryId + "&type=multiple").readText()

                    resp = quizDataJson
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    resp = e.message
                } catch (e: Exception) {
                    e.printStackTrace()
                    resp = e.message
                }

                return resp
            }

            override fun onPostExecute(result: String?) {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                activity.progressBar.visibility = View.GONE

                activity.rectangle_1.visibility = View.VISIBLE

                val quizDataJson: QuizData? = Klaxon().parse<QuizData>(result.let { it }.toString())


                var formattedQuizData: QuizFormattedData = QuizFormattedData()





                quizDataJson?.results?.forEach {
                    var correctOption: Option = Option()
                    var options: ArrayList<Option> = ArrayList()
                    correctOption.isCorrect = true
                    correctOption.text = it.correct_answer
                    var incorrectAnswer1: Option = Option()
                    var incorrectAnswer2: Option = Option()
                    var incorrectAnswer3: Option = Option()

                    incorrectAnswer1.isCorrect = false
                    incorrectAnswer1.text = it.incorrect_answers[0]
                    incorrectAnswer2.isCorrect = false
                    incorrectAnswer2.text = it.incorrect_answers[1]
                    incorrectAnswer3.isCorrect = false
                    incorrectAnswer3.text = it.incorrect_answers[2]
                    options.add(correctOption)
                    options.add(incorrectAnswer1)
                    options.add(incorrectAnswer2)
                    options.add(incorrectAnswer3)

                    Collections.shuffle(options)

                    var formattedItem: QuizFormattedItem = QuizFormattedItem()
                    formattedItem.category = it.category
                    formattedItem.difficulty = it.difficulty
                    formattedItem.type = it.type
                    formattedItem.question = it.question
                    formattedItem.correct_answer = it.correct_answer
                    formattedItem.incorrect_answers = it.incorrect_answers
                    formattedItem.options = options
                    formattedQuizData.results.add(formattedItem)

                }
                activity.quizData = formattedQuizData
                activity.question.text =
                    formattedQuizData.results[activity.currentQuestion].question


                activity.option_text1.text =
                    "   " + formattedQuizData.results[activity.currentQuestion].options[0].text
                activity.option_text2.text =
                    "   " + formattedQuizData.results[activity.currentQuestion].options[1].text
                activity.option_text3.text =
                    "   " + formattedQuizData.results[activity.currentQuestion].options[2].text
                activity.option_text4.text =
                    "   " + formattedQuizData.results[activity.currentQuestion].options[3].text
                activity.option_text1.visibility = View.VISIBLE
                activity.option_text2.visibility = View.VISIBLE
                activity.option_text3.visibility = View.VISIBLE
                activity.option_text4.visibility = View.VISIBLE
                activity.timer.start()
            }


        }
    }


}