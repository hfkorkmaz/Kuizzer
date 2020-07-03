package com.hfkorkmaz.kuizzer

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_categories.*
import java.lang.ref.WeakReference
import java.net.URL

class CategoriesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

//        val categoriesJson = URL("https://opentdb.com/api_category.php").readText()
//        print(categoriesJson.javaClass.name)
        val task = GetCategoriesTask(this)
        task.execute()
    }

    companion object {
        class GetCategoriesTask internal constructor(context: CategoriesActivity) :
            AsyncTask<Int, String, String?>() {
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
                Log.d("categories",result.let { it })
            }


        }
    }


}