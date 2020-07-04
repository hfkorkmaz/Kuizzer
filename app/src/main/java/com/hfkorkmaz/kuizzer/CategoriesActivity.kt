package com.hfkorkmaz.kuizzer

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.beust.klaxon.Klaxon
import com.hfkorkmaz.kuizzer.adapters.CategoriesAdapter
import com.hfkorkmaz.kuizzer.models.Categories
import kotlinx.android.synthetic.main.activity_categories.*
import java.lang.ref.WeakReference
import java.net.URL

class CategoriesActivity : AppCompatActivity() {

    var adapter : CategoriesAdapter? = null

    var categoriesData : Categories? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val task = GetCategoriesTask(this)
        task.execute()

    }

    fun startGame(categoryId:Int?, categoryName:String?){
        val intent = Intent(applicationContext, GameActivity::class.java)
        intent.putExtra("categoryId", categoryId)
        intent.putExtra("categoryName", categoryName)
        startActivity(intent)
        finish()
    }

    companion object {
        class GetCategoriesTask internal constructor(context: CategoriesActivity) :
            AsyncTask<Int, String, String?>(), CategoriesAdapter.OnCategoryListener {
            private var resp: String? = null
            private val activityReference: WeakReference<CategoriesActivity> =
                WeakReference(context)

            override fun onPreExecute() {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                activity.progressBar.visibility = View.VISIBLE
            }

            override fun doInBackground(vararg params: Int?): String? {
                try {
                    val categoriesJson = URL("https://opentdb.com/api_category.php").readText()

                    resp = categoriesJson
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
                activity.recyclerView.visibility = View.VISIBLE
                val categoriesJson : Categories? = Klaxon().parse<Categories>(result.let{it}.toString())
                activity.categoriesData = categoriesJson
                var layoutManager = LinearLayoutManager(activity)

                activity.recyclerView.layoutManager = layoutManager

                activity.adapter = CategoriesAdapter(categoriesJson!!.trivia_categories, this)
                activity.recyclerView.adapter = activity.adapter

            }

            override fun onCategoryClick(adapterPosition: Int) {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                val id = activity.categoriesData?.trivia_categories?.get(adapterPosition)?.id
                val name = activity.categoriesData?.trivia_categories?.get(adapterPosition)?.name
                activity.startGame(id, name)

            }


        }
    }




}
