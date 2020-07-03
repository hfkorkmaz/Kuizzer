package com.hfkorkmaz.kuizzer

import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        welcome_text.text = "Welcome "+currentUser?.displayName.toString()+"!"

    }

    fun clickPlay(view: View){
        val intent = Intent(applicationContext, CategoriesActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun logOut(view:View){
        auth.signOut()
        val intent = Intent(applicationContext, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}